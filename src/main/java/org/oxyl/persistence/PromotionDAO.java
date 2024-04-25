package org.oxyl.persistence;

import org.oxyl.mapper.MapperPromotion;
import org.oxyl.model.Promotion;
import org.oxyl.newro.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PromotionDAO {

    private static Logger logger = LoggerFactory.getLogger(PromotionDAO.class);

    public static void afficherPagePromotion(int pageNumber, Page<Promotion> page) {

        String sql = "SELECT id, name FROM promotion LIMIT ? OFFSET ?";

        try(Connection conn = MySqlConnexion.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){
            stmt.setInt(1, page.getNbRow());
            stmt.setInt(2, (pageNumber - 1) * page.getNbRow());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                page.addContent(new MapperPromotion().rsToPromotion(rs).get());
            }

            page.display();
            page.emptyContent();

        } catch (SQLException e) {
            logger.error("Erreur lors de l'affichage de la page de promotion", e);
            throw new RuntimeException(e);
        }
    }
}
