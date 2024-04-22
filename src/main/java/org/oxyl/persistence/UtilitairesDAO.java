package org.oxyl.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilitairesDAO {

    public static int getMaxID(Connection conn) {
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
    }

    public static int getTotalPages(String table, int rowsPerPage){
        MySqlConnexion connection = MySqlConnexion.getInstance();
        String countQuery = "SELECT COUNT(*) FROM " + table;

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(countQuery);) {
            ResultSet rs = stmt.executeQuery(countQuery);
            rs.next();
            int totalRows = rs.getInt(1);

            // Calcul du nombre total de pages
            return (int) Math.ceil((double) totalRows / rowsPerPage);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }finally {
            connection.closeConnection();
        }
    }
}
