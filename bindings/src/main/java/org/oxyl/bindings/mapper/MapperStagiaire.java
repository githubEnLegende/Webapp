package org.oxyl.bindings.mapper;

import org.oxyl.bindings.dto.stagiairedto.StagiaireDTOAdd;
import org.oxyl.bindings.dto.stagiairedto.StagiaireDTOEdit;
import org.oxyl.bindings.dto.stagiairedto.StagiaireDTOPage;
import org.oxyl.core.model.Promotion;
import org.oxyl.core.model.Stagiaire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MapperStagiaire {

    private static final Logger logger = LoggerFactory.getLogger(MapperStagiaire.class);
    private final MapperPromotion promotionMapper;
    private final MapperDate mapperDate;

    public MapperStagiaire(MapperPromotion promotionMapper, MapperDate mapperDate) {
        this.promotionMapper = promotionMapper;
        this.mapperDate = mapperDate;
    }

    public Stagiaire dtoAddToModel(StagiaireDTOAdd stagiaire) {
        Promotion promotion = new Promotion.PromotionBuilder(
                Integer.parseInt(stagiaire.promotionId()), stagiaire.promotionName())
                .build();
        return new Stagiaire.StagiaireBuilder()
                .firstName(stagiaire.firstName())
                .lastName(stagiaire.lastName())
                .arrival(mapperDate.stringtoLocalDate(stagiaire.arrival()))
                .formationOver(mapperDate.stringtoLocalDate(stagiaire.formationOver()))
                .promotion(promotion)
                .build();
    }

    public Stagiaire dtoEditToModel(StagiaireDTOEdit stagiaire) {
        Promotion promotion = new Promotion.PromotionBuilder(
                Integer.parseInt(stagiaire.promotionId()), stagiaire.promotionName())
                .build();
        return new Stagiaire.StagiaireBuilder()
                .id(stagiaire.id())
                .firstName(stagiaire.firstName())
                .lastName(stagiaire.lastName())
                .arrival(mapperDate.stringtoLocalDate(stagiaire.arrival()))
                .formationOver(mapperDate.stringtoLocalDate(stagiaire.formationOver()))
                .promotion(promotion)
                .build();
    }

    public StagiaireDTOAdd modelToDtoAdd(Stagiaire stagiaire) {
        return new StagiaireDTOAdd(
                stagiaire.getFirstName(),
                stagiaire.getLastName(),
                mapperDate.localDateToString(stagiaire.getArrival()),
                mapperDate.localDateToString(stagiaire.getFormationOver()),
                Long.toString(stagiaire.getPromotion().getId()),
                stagiaire.getPromotion().getName());
    }

    public StagiaireDTOEdit modelToDtoEdit(Stagiaire stagiaire) {
        return new StagiaireDTOEdit(
                stagiaire.getId(),
                stagiaire.getFirstName(),
                stagiaire.getLastName(),
                mapperDate.localDateToString(stagiaire.getArrival()),
                mapperDate.localDateToString(stagiaire.getFormationOver()),
                Long.toString(stagiaire.getPromotion().getId()),
                stagiaire.getPromotion().getName());
    }

    public StagiaireDTOPage modelToDtoPage(Stagiaire stagiaire) {
        return new StagiaireDTOPage(
                stagiaire.getId(),
                stagiaire.getLastName(),
                stagiaire.getFirstName(),
                mapperDate.localDateToString(stagiaire.getArrival()),
                mapperDate.localDateToString(stagiaire.getFormationOver()),
                stagiaire.getPromotion().getName()
        );
    }

    public List<StagiaireDTOPage> listModelToListDtoPage(List<Stagiaire> stagiaires) {
        return stagiaires.stream().map(this::modelToDtoPage).toList();
    }
}
