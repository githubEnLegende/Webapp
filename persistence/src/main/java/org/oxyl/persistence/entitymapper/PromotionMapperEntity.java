package org.oxyl.persistence.entitymapper;

import org.oxyl.core.model.Promotion;
import org.oxyl.persistence.entities.PromotionEntity;
import org.springframework.stereotype.Component;

@Component
public class PromotionMapperEntity {

    public Promotion toModel(PromotionEntity promotionEntity) {
        return new Promotion.PromotionBuilder(
                promotionEntity.getId(),
                promotionEntity.getName()
        ).build();
    }
}
