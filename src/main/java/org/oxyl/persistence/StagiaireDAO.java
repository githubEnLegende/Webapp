package org.oxyl.persistence;

import com.zaxxer.hikari.HikariDataSource;
import org.oxyl.mapper.MapperStagiaire;
import org.oxyl.model.Stagiaire;
import org.oxyl.model.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class StagiaireDAO {

    private static final Logger logger = LoggerFactory.getLogger(StagiaireDAO.class);
    private HikariDataSource dataSource;
    private MapperStagiaire mapperStagiaire;

    public StagiaireDAO(HikariDataSource dataSource, MapperStagiaire mapperStagiaire) {
        this.dataSource = dataSource;
        this.mapperStagiaire = mapperStagiaire;
    }

    public int countStagiaire() {
        String query = "SELECT COUNT(*) FROM intern";

        try(Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            logger.error("Error while counting stagiaire", e);
            return 0;
        }
    }

    public Optional<List<Stagiaire>> getAllStagiaires() {
        String sql = "SELECT id, first_name, last_name, arrival, formation_over, promotion_id FROM intern";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            List<Stagiaire> stagiaires = new ArrayList<>();

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                stagiaires.add(mapperStagiaire.rsToStagiaire(rs).get());
            }
            return Optional.of(stagiaires);
        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération des stagiaires.", e);
            return Optional.empty();
        }
    }
    public void getPageStagiaire(Page<Stagiaire> page) {

        String sql = "SELECT intern.id, first_name, last_name, arrival, formation_over, promotion_id, promotion.name " +
                "FROM intern LEFT JOIN promotion ON intern.promotion_id = promotion.id";

        StringBuilder query = new StringBuilder(sql);
        query.append(" ORDER BY ").append(page.getOrder()).append(" LIMIT ? OFFSET ?;");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            stmt.setInt(1, page.getNbRow());
            stmt.setInt(2, (page.getPageNumber() - 1) * page.getNbRow());


            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                Stagiaire stagiaire = null;
                Optional<Stagiaire> intern = mapperStagiaire.rsToStagiaire(rs);
                if (intern.isPresent()) {
                    stagiaire = intern.get();
                }
                page.addContent(stagiaire);
            }

        } catch (SQLException e) {
            logger.error("Erreur Lors de l'affichage de la page des stagiaires.", e);
        }

    }

    public int getPageStagiaire(String name, Page<Stagiaire> page){

        String sql = "SELECT intern.id, first_name, last_name, arrival, formation_over, promotion_id, promotion.name " +
                "FROM intern LEFT JOIN promotion ON intern.promotion_id = promotion.id WHERE first_name LIKE ? OR last_name LIKE ?";


        String countSql = "SELECT COUNT(*) FROM intern WHERE first_name LIKE ? OR last_name LIKE ?";

        StringBuilder query = new StringBuilder(sql);
        query.append(" ORDER BY ").append(page.getOrder()).append(" LIMIT ? OFFSET ?;");

        name = "%" + name + "%";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmtData = conn.prepareStatement(query.toString());
             PreparedStatement stmtCount = conn.prepareStatement(countSql)) {

            stmtData.setString(1, name);
            stmtData.setString(2, name);
            stmtData.setInt(3, page.getNbRow());
            stmtData.setInt(4, (page.getPageNumber() - 1) * page.getNbRow());

            stmtCount.setString(1, name);
            stmtCount.setString(2, name);

            try (ResultSet rsData = stmtData.executeQuery();
                ResultSet rsCount = stmtCount.executeQuery();) {
                while (rsData.next()) {
                    Stagiaire stagiaire = null;
                    Optional<Stagiaire> intern = mapperStagiaire.rsToStagiaire(rsData);
                    if (intern.isPresent()) {
                        stagiaire = intern.get();
                    }
                    page.addContent(stagiaire);
                }
                rsCount.next();
                return rsCount.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Probleme lors de la connexion pour l'affichage sur search", e);
            return 0;
        }
    }

    public Optional<Stagiaire> detailStagiaire(int id) {

        String sql = "SELECT intern.id, first_name, last_name, arrival, formation_over, promotion_id, promotion.name " +
                "FROM intern LEFT JOIN promotion ON intern.promotion_id = promotion.id WHERE intern.id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id); // Remplacez 1 par l'ID du stagiaire désiré
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Affichage des données
                    Optional<Stagiaire> random = mapperStagiaire.rsToStagiaire(rs);
                    System.out.println(random);
                    return random;
                } else {
                    System.out.println("Aucun résultat trouvé.");
                }
            }
        } catch (SQLException e) {
            logger.error("Erreur requête SQL pour le détail stagiaire");
            System.out.println(e);
            return Optional.empty();
        }
        return Optional.empty();
    }

    public void insertIntern(Stagiaire intern) {
        String sql = "INSERT INTO intern (first_name, last_name, arrival, formation_over, promotion_id)"
                + " VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, intern.getFirstName());
            stmt.setString(2, intern.getLastName());

            if (intern.getArrival() != null){
                Timestamp timestamp = Timestamp.valueOf(intern.getArrival().atStartOfDay());
                stmt.setTimestamp(3, timestamp);
            }else{
                stmt.setTimestamp(3, null);
            }

            if (intern.getFormationOver() != null){
                Timestamp timestamp = Timestamp.valueOf(intern.getFormationOver().atStartOfDay());
                stmt.setTimestamp(4, timestamp);
            }else{
                stmt.setTimestamp(4, null);
            }
            stmt.setInt(5, intern.getPromotion().getId());

            // Exécution de la requête d'insertion
            System.out.println(stmt);
            stmt.executeUpdate();
            System.out.println("Stagiaire inséré avec succès !");
        } catch (SQLException e) {
            logger.error("erreur requête sql lors de l'insertion d'un stagiaire");
        }
    }

    public void deleteIntern(int id) {

        String sql = "DELETE FROM intern WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            // Exécution de la requête de suppression
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Stagiaire supprimé avec succès !");
            } else {
                System.out.println("Aucun stagiaire trouvé avec l'ID spécifié.");
            }
        } catch (SQLException e) {
            logger.error("erreur SQL lors de la supression d'un stagiaire", e);
        }
    }

    public void updateIntern(String prenom, String nom, String arrive, String finFormation, int promo, int id) {
        String sql = "UPDATE intern SET ";
        List<Object> parameters = new ArrayList<>();

        if (prenom != null && !prenom.isEmpty()) {
            sql += "first_name = ?, ";
            parameters.add(prenom);
        }
        if (nom != null && !nom.isEmpty()) {
            sql += "last_name = ?, ";
            parameters.add(nom);
        }
        if (promo != 0) {
            sql += "promotion_id = ?, ";
            parameters.add(promo);
        }
        if (arrive != null && !arrive.isEmpty()) {
            sql += "arrival = ?, ";
            parameters.add(LocalDate.parse(arrive));
        }
        if (finFormation != null && !finFormation.isEmpty()) {
            sql += "formation_over = ?, ";
            parameters.add(LocalDate.parse(finFormation));
        }

        // Supprimer la dernière virgule et l'espace
        sql = sql.substring(0, sql.length() - 2);

        sql += " WHERE id = ?";
        parameters.add(id);

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Définir les paramètres
            int index = 1;
            for (Object parameter : parameters) {
                stmt.setObject(index, parameter);
                System.out.println(parameter);
                index++;
            }
            System.out.println(stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Erreur SQL lors de la mise à jour d'un stagiaire", e);
        }
    }


}
