package org.oxyl.persistence;

import jdk.jshell.execution.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilitairesDAO {

    private static UtilitairesDAO instance;
    private final static Logger logger = LoggerFactory.getLogger(UtilitairesDAO.class);

    public static UtilitairesDAO getInstance() {
        if (instance == null) {
            instance = new UtilitairesDAO();
        }
        return instance;
    }

    public int getMaxID() {
        try (Connection conn = DataSource.getConnection()) {
            int maxId = 0;
            String sql = "SELECT MAX(ID) FROM intern";
            ResultSet rs = null;
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    return maxId = rs.getInt(1);
                }
            } catch (SQLException e) {
                logger.error("Erreur lors de la récupération du max ID", e);
            }
            return maxId;
        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération du max ID", e);
        }
        return 0;
    }

    public int getTotalPages(String table, int rowsPerPage) {

        String countQuery = "SELECT COUNT(*) FROM " + table;

        try (Connection connection = DataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(countQuery);) {
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int totalRows = rs.getInt(1);

            // Calcul du nombre total de pages
            return (int) Math.ceil((double) totalRows / rowsPerPage);
        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération du total des pages", e);
            return 0;
        }
    }
}
