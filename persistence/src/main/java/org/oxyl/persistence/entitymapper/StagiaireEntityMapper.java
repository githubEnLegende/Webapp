package org.oxyl.persistence.entitymapper;
import org.oxyl.core.model.Stagiaire;
import org.oxyl.core.model.Promotion;

import org.oxyl.persistence.entities.InternEntity;
import org.oxyl.persistence.entities.PromotionEntity;
import org.springframework.stereotype.Component;

@Component
public class StagiaireEntityMapper {

    public Stagiaire toModel(InternEntity internEntity) {
        Promotion promo = new Promotion.PromotionBuilder(
                internEntity.getPromotion().getId(),
                internEntity.getPromotion().getName()
        ).build();

        return new Stagiaire.StagiaireBuilder()
                .id(internEntity.getId())
                .firstName(internEntity.getFirstName())
                .lastName(internEntity.getLastName())
                .arrival(internEntity.getArrival())
                .promotion(promo)
                .formationOver(internEntity.getFormationOver())
                .build();
    }

    public InternEntity toEntity(Stagiaire stagiaire) {
        return new InternEntity(
                stagiaire.getFirstName(),
                stagiaire.getLastName(),
                stagiaire.getArrival(),
                stagiaire.getFormationOver(),
                new PromotionEntity(stagiaire.getPromotion().getId(), stagiaire.getPromotion().getName())
        );
    }
}
