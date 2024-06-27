package org.oxyl.service.service;

import org.oxyl.core.model.Promotion;
import org.oxyl.persistence.PromotionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    private static final Logger logger = LoggerFactory.getLogger(PromotionService.class);
    private final PromotionDAO promotionDAO;

    public PromotionService(PromotionDAO promotionDAO) {
        this.promotionDAO = promotionDAO;
    }

    public List<Promotion> getAllPromotion() {
        logger.info("getAllPromotion");
        return promotionDAO.getAllPromotion();
    }
}
