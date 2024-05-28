package org.oxyl.persistence;

import org.oxyl.mapper.MapperStagiaire;
import org.oxyl.model.Page;
import org.oxyl.model.Stagiaire;
import org.oxyl.persistence.repository.StagiaireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class StagiaireDAO {

    private static final Logger logger = LoggerFactory.getLogger(StagiaireDAO.class);
    private final MapperStagiaire mapperStagiaire;
    private final JdbcTemplate jdbcTemplate;
    private final StagiaireRepository stagiaireRepository;

    public StagiaireDAO(MapperStagiaire mapperStagiaire, JdbcTemplate jdbcTemplate, StagiaireRepository stagiaireRepository) {
        this.mapperStagiaire = mapperStagiaire;
        this.jdbcTemplate = jdbcTemplate;
        this.stagiaireRepository = stagiaireRepository;
    }

    public long countStagiaire() {
        try {
            return stagiaireRepository.count();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération du nombre total de stagiaires", e);
            return 0;
        }
    }

    public Optional<List<Stagiaire>> getAllStagiaires() {
        String sql = "SELECT s.id, s.first_name, s.last_name, s.arrival, s.formation_over, p.id AS promotion_id, p.name AS promotion_name FROM stagiaire s JOIN promotion p ON s.promotion_id = p.id";
        List<Stagiaire> stagiaires = new ArrayList<>();
        try {
            stagiaires = jdbcTemplate.query(sql, mapperStagiaire);
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération de tous les stagiaires", e);
        }
        return Optional.of(stagiaires);
    }

    public void getPageStagiaire(Page<Stagiaire> page) {

        String sql = "SELECT intern.id, first_name, last_name, arrival, formation_over, promotion_id, promotion.name " +
                "FROM intern LEFT JOIN promotion ON intern.promotion_id = promotion.id";

        StringBuilder query = new StringBuilder(sql);
        query.append(" ORDER BY ").append(page.getOrder()).append(" LIMIT ? OFFSET ?;");

        try {
            List<Stagiaire> stagiaires = jdbcTemplate.query(query.toString(), mapperStagiaire, page.getNbRow(), (page.getPageNumber() - 1) * page.getNbRow());
            page.setContent(stagiaires);
        } catch (DataAccessException e) {
            logger.error("Erreur lors de l'affichage de la page des stagiaires.", e);
        }

    }

    @Transactional
    public long getPageStagiaire(String name, Page<Stagiaire> page) {

        String sql = "SELECT intern.id, first_name, last_name, arrival, formation_over, promotion_id, promotion.name " +
                "FROM intern LEFT JOIN promotion ON intern.promotion_id = promotion.id WHERE first_name LIKE ? OR last_name LIKE ?";
        String countSql = "SELECT COUNT(*) FROM intern WHERE first_name LIKE ? OR last_name LIKE ?";
        StringBuilder query = new StringBuilder(sql);
        query.append(" ORDER BY ").append(page.getOrder()).append(" LIMIT ? OFFSET ?;");
        name = "%" + name + "%";

        int count = 0;
        try {
            List<Stagiaire> stagiaires = jdbcTemplate.query(query.toString(), mapperStagiaire, name, name, page.getNbRow(), (page.getPageNumber() - 1) * page.getNbRow());
            page.setContent(stagiaires);

            count = jdbcTemplate.queryForObject(countSql, Integer.class, name, name);
        } catch (DataAccessException e) {
            logger.error("Problème lors de la connexion pour l'affichage sur search", e);
        }
        return count;
    }

    public Optional<Stagiaire> detailStagiaire(long id) {

        String sql = "SELECT intern.id, first_name, last_name, arrival, formation_over, promotion_id, promotion.name " +
                "FROM intern LEFT JOIN promotion ON intern.promotion_id = promotion.id WHERE intern.id = ?";

        try {
            Stagiaire stagiaire = jdbcTemplate.queryForObject(sql, mapperStagiaire, id);
            return Optional.of(stagiaire);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Aucun résultat trouvé.");
            return Optional.empty();
        } catch (DataAccessException e) {
            logger.error("Erreur requête SQL pour le détail stagiaire");
            return Optional.empty();
        }
    }

    public void insertIntern(Stagiaire intern) {
        String sql = "INSERT INTO intern (first_name, last_name, arrival, formation_over, promotion_id)"
                + " VALUES (?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql, intern.getFirstName(),
                    intern.getLastName(),
                    intern.getArrival() != null ? Timestamp.valueOf(intern.getArrival().atStartOfDay()) : null,
                    intern.getFormationOver() != null ? Timestamp.valueOf(intern.getFormationOver().atStartOfDay()) : null,
                    intern.getPromotion().getId());

            System.out.println("Stagiaire inséré avec succès !");
        } catch (DataAccessException e) {
            logger.error("erreur requête sql lors de l'insertion d'un stagiaire");
        }
    }

    public void deleteIntern(long id) {

        String sql = "DELETE FROM intern WHERE id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                System.out.println("Stagiaire supprimé avec succès !");
            } else {
                System.out.println("Aucun stagiaire trouvé avec l'ID spécifié.");
            }
        } catch (DataAccessException e) {
            logger.error("erreur SQL lors de la supression d'un stagiaire", e);
        }
    }

    public void updateIntern(Stagiaire intern) {
        String sql = "UPDATE intern SET first_name = ?, last_name = ?, promotion_id = ?, arrival = ?, formation_over = ? WHERE id = ?";

        try {
            jdbcTemplate.update(sql, intern.getFirstName(), intern.getLastName(),
                    intern.getPromotion().getId(), intern.getArrival(),
                    intern.getFormationOver(), intern.getId());
        } catch (DataAccessException e) {
            logger.error("Erreur SQL lors de la mise à jour d'un stagiaire", e);
        }
    }


}
