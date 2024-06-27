package org.oxyl.persistence;

import org.oxyl.core.model.Promotion;
import org.oxyl.persistence.entitymapper.PromotionMapperEntity;
import org.oxyl.persistence.repository.PromotionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PromotionDAO {

    private static final Logger logger = LoggerFactory.getLogger(PromotionDAO.class);
    private final PromotionMapperEntity mapperPromotion;
    private final PromotionRepository promotionRepository;

    public PromotionDAO(PromotionMapperEntity mapperPromotion/*, JdbcTemplate jdbcTemplate*/, PromotionRepository promotionRepository) {
        this.mapperPromotion = mapperPromotion;
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> getAllPromotion() {
        return promotionRepository.findAll().stream().map(mapperPromotion::toModel).toList();
    }
}
