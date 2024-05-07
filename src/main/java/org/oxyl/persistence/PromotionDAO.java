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

    private static PromotionDAO instance;

    public static PromotionDAO getInstance() {
        if (instance == null) {
            instance = new PromotionDAO();
        }
        return instance;
    }

    public List<Promotion> getAllPromotion() {

        List<Promotion> listPromo = new ArrayList<>();
        String sql = "SELECT id, name FROM promotion";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listPromo.add(MapperPromotion.getInstance().rsToPromotion(rs).get());
            }
            return listPromo;
        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération des promotions", e);
        }

        return listPromo;
    }

    public void afficherPagePromotion(int pageNumber, Page<Promotion> page) {

        String sql = "SELECT id, name FROM promotion LIMIT ? OFFSET ?";

        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, page.getNbRow());
            stmt.setInt(2, (pageNumber - 1) * page.getNbRow());
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                page.addContent(MapperPromotion.getInstance().rsToPromotion(rs).get());
            }

            page.display();
            page.emptyContent();

        } catch (SQLException e) {
            logger.error("Erreur lors de l'affichage de la page de promotion", e);
            throw new RuntimeException(e);
        }
    }

    public Optional<Promotion> getPromotion(int id) {

        try (Connection conn = DataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT id, name FROM promotion WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs2 = stmt.executeQuery();
            rs2.next();
            Optional<Promotion> promoOpt = MapperPromotion.getInstance().rsToPromotion(rs2);
            return promoOpt;

        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération de la promotion", e);
            return Optional.empty();
        }
    }
}
