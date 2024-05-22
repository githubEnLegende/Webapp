package org.oxyl.persistence;

import org.oxyl.mapper.MapperPromotion;
import org.oxyl.model.Promotion;
import org.oxyl.model.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PromotionDAO {

    private static final Logger logger = LoggerFactory.getLogger(PromotionDAO.class);
    private final MapperPromotion mapperPromotion;
    private final JdbcTemplate jdbcTemplate;

    public PromotionDAO(MapperPromotion mapperPromotion, JdbcTemplate jdbcTemplate) {
        this.mapperPromotion = mapperPromotion;
        this.jdbcTemplate = jdbcTemplate;

    }

    public List<Promotion> getAllPromotion() {

        List<Promotion> listPromo = new ArrayList<>();
        String sql = "SELECT id, name FROM promotion";
        try {
            listPromo = jdbcTemplate.query(sql, mapperPromotion);
            return listPromo;
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération des promotions", e);
            return listPromo;
        }
    }

    public void afficherPagePromotion(Page<Promotion> page) {
        String sql = "SELECT id, name FROM promotion LIMIT ? OFFSET ?";
        try {
            page.setContent(jdbcTemplate.query(sql, mapperPromotion));
            page.display();
            page.emptyContent();

        } catch (DataAccessException e) {
            logger.error("Erreur lors de l'affichage de la page de promotion", e);
        }
    }
}
