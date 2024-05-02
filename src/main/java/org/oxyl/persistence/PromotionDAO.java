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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromotionDAO {

    private static Logger logger = LoggerFactory.getLogger(PromotionDAO.class);

    public static List<Promotion> getAllPromotion(){

        List<Promotion> listPromo = new ArrayList<>();
        String sql = "SELECT id, name FROM promotion";
        try(Connection conn = MySqlConnexion.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listPromo.add(new MapperPromotion().rsToPromotion(rs).get());
            }
            return listPromo;
        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération des promotions", e);
        }

        return listPromo;
    }

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

    public static Optional<Promotion> getPromotion(int id){
        Connection conn = MySqlConnexion.getInstance().getConnection();
        try(
            PreparedStatement stmt = conn.prepareStatement("SELECT id, name FROM promotion WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs2 = stmt.executeQuery();
            rs2.next();
            Optional<Promotion> promoOpt = new MapperPromotion().rsToPromotion(rs2);
            return promoOpt;

        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération de la promotion", e);
            return Optional.empty();
        }
    }
}
