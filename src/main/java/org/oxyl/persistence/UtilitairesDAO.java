package org.oxyl.persistence;

import jdk.jshell.execution.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilitairesDAO {

    private static UtilitairesDAO instance;

    public static UtilitairesDAO getInstance() {
        if (instance == null) {
            instance = new UtilitairesDAO();
        }
        return instance;
    }

    public int getMaxID() {
        try (Connection conn = MySqlConnexion.getInstance().getConnection()) {
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
                e.printStackTrace();
            }
            return maxId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalPages(String table, int rowsPerPage) {

        String countQuery = "SELECT COUNT(*) FROM " + table;

        try (Connection connection = MySqlConnexion.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(countQuery);) {
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int totalRows = rs.getInt(1);

            // Calcul du nombre total de pages
            return (int) Math.ceil((double) totalRows / rowsPerPage);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
