package org.oxyl.persistence;

import org.oxyl.mapper.MapperStagiaire;
import org.oxyl.model.Stagiaire;
import org.oxyl.newro.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StagiaireDAO {

    private static Logger logger = LoggerFactory.getLogger(StagiaireDAO.class);

    public static Optional<List<Stagiaire>> getAllStagiaires() {
        String sql = "SELECT id, first_name, last_name, arrival, formation_over, promotion_id FROM intern";
        try (Connection conn = MySqlConnexion.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            List<Stagiaire> stagiaires = new ArrayList<>();

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                stagiaires.add(new MapperStagiaire().rsToStagiaire(rs).get());
            }
            return Optional.of(stagiaires);
        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération des stagiaires.", e);
            return Optional.empty();
        }
    }
    public static void afficherPageStagiaire(int pageNumber, Page<Stagiaire> page) {

        String sql = "SELECT id, first_name, last_name, arrival, formation_over, promotion_id FROM intern LIMIT ? OFFSET ?";
        try (Connection conn = MySqlConnexion.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setInt(1, page.getNbRow());
            stmt.setInt(2, (pageNumber - 1) * page.getNbRow());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                page.addContent(new MapperStagiaire().rsToStagiaire(rs).get());
            }
            page.display();
            page.emptyContent();
        } catch (SQLException e) {
            logger.error("Erreur Lors de l'affichage de la page des stagiaires.", e);
        }
    }

    public static Optional<Stagiaire> detailStagiaire(int id) {

        String sql = "SELECT id, first_name, last_name, arrival, formation_over, promotion_id FROM intern WHERE id = ?";
        try (Connection conn = MySqlConnexion.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id); // Remplacez 1 par l'ID du stagiaire désiré
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Affichage des données
                    Stagiaire random = new Stagiaire.StagiaireBuilder(rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getTimestamp("arrival")
                                    == null ? null : rs.getTimestamp("arrival").toLocalDateTime().toLocalDate(),
                            rs.getInt("promotion_id"))
                            .formationOver(rs.getTimestamp("formation_over")
                                    == null ? null : rs.getTimestamp("formation_over").toLocalDateTime().toLocalDate())
                            .build();
                    System.out.println(random.toString());
                    return Optional.of(random);
                } else {
                    System.out.println("Aucun résultat trouvé.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.empty();
    }

    public static void insertIntern(Stagiaire intern) {
        String sql = "INSERT INTO intern (first_name, last_name, arrival, formation_over, promotion_id)"
                + " VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = MySqlConnexion.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, intern.getFirstName());
            stmt.setString(2, intern.getLastName());

            if (intern.getArrival() != null){
                Timestamp timestamp = Timestamp.valueOf(intern.getArrival().atStartOfDay());
                stmt.setTimestamp(3, timestamp);
            }else{
                stmt.setTimestamp(3, null);
            }
            stmt.setTimestamp(4, null);

            stmt.setInt(5, intern.getPromotion());

            // Exécution de la requête d'insertion
            stmt.executeUpdate();
            System.out.println("Stagiaire inséré avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteIntern(int id) {

        String sql = "DELETE FROM intern WHERE id = ?";

        try (Connection conn = MySqlConnexion.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setLong(1, id);

            // Exécution de la requête de suppression
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Stagiaire supprimé avec succès !");
            } else {
                System.out.println("Aucun stagiaire trouvé avec l'ID spécifié.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateIntern(String prenom, String nom, String arrive, int promo, int id) {
        PreparedStatement stmt = null;

        try (Connection conn = MySqlConnexion.getInstance().getConnection();) {

            String sql = "UPDATE intern SET ";
            if (prenom != null) {
                sql = sql.concat("first_name = \"" + prenom + "\", ");
            }

            if (nom != null) {
                sql = sql.concat("last_name = \"" + nom + "\", ");
            }

            if (promo != 0) {
                sql = sql.concat("promotion_id = " + promo + ", ");
            }
            if (arrive != null) {
                sql = sql.concat("arrival = \""
                        + LocalDate.parse(arrive, DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\", ");
            }

            sql = sql.substring(0, sql.length() - 2);
            sql = sql.concat(" WHERE id = " + id);

            System.out.println(sql);
            stmt = conn.prepareStatement(sql);

            // Exécution de la requête de mise à jour
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Stagiaire mis à jour avec succès !");
            } else {
                System.out.println("Aucun stagiaire trouvé avec l'ID spécifié.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
