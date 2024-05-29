package org.oxyl.persistence;

import org.oxyl.mapper.MapperPromotion;
import org.oxyl.model.Promotion;
import org.oxyl.persistence.repository.PromotionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PromotionDAO {

    private static final Logger logger = LoggerFactory.getLogger(PromotionDAO.class);
    private final MapperPromotion mapperPromotion;
    //    private final JdbcTemplate jdbcTemplate;
    private final PromotionRepository promotionRepository;

    public PromotionDAO(MapperPromotion mapperPromotion/*, JdbcTemplate jdbcTemplate*/, PromotionRepository promotionRepository) {
        this.mapperPromotion = mapperPromotion;
//        this.jdbcTemplate = jdbcTemplate;
        this.promotionRepository = promotionRepository;
    }

//    public List<Promotion> getAllPromotion() {
//
//        List<Promotion> listPromo = new ArrayList<>();
//        String sql = "SELECT id, name FROM promotion";
//        try {
//            listPromo = jdbcTemplate.query(sql, mapperPromotion);
//            return listPromo;
//        } catch (DataAccessException e) {
//            logger.error("Erreur lors de la récupération des promotions", e);
//            return listPromo;
//        }
//    }

    public List<Promotion> getAllPromotion() {
        return promotionRepository.findAll().stream().map(mapperPromotion::toModel).toList();
    }

//    public void afficherPagePromotion(Page<Promotion> page) {
//        String sql = "SELECT id, name FROM promotion LIMIT ? OFFSET ?";
//        try {
//            page.setContent(jdbcTemplate.query(sql, mapperPromotion));
//            page.display();
//            page.emptyContent();
//
//        } catch (DataAccessException e) {
//            logger.error("Erreur lors de l'affichage de la page de promotion", e);
//        }
//    }
}
