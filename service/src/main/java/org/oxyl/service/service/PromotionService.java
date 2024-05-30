package org.oxyl.service.service;

import org.oxyl.core.model.Promotion;
import org.oxyl.persistence.PromotionDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    private final PromotionDAO promotionDAO;

    public PromotionService(PromotionDAO promotionDAO) {
        this.promotionDAO = promotionDAO;
    }

    public List<Promotion> getAllPromotion() {
        return promotionDAO.getAllPromotion();
    }

//    public void afficherPagePromotion(Page<Promotion> page) {
//        promotionDAO.afficherPagePromotion(page);
//    }
}
