package org.oxyl.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UtilitairesDAO {

    private final static Logger logger = LoggerFactory.getLogger(UtilitairesDAO.class);

    private final JdbcTemplate jdbcTemplate;

    public UtilitairesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMaxID() {
        int maxId = 0;

        String sql = "SELECT MAX(ID) FROM intern";
        try {
            maxId = jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Aucun résultat trouvé pour la récupération du max ID", e);
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération du max ID", e);
        }
        return maxId;
    }

    public int getTotalPages(String table, int rowsPerPage) {
        String countQuery = "SELECT COUNT(*) FROM " + table;
        int totalRows = 0;
        try {
            totalRows = jdbcTemplate.queryForObject(countQuery, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Aucun résultat trouvé pour la récupération du max des pages", e);
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération du max des pages", e);
        }
        return (int) Math.ceil((double) totalRows / rowsPerPage);
    }

    public int getTotalPages(int count, int rowsPerPage) {
        return (int) Math.ceil((double) count / rowsPerPage);
    }
}
