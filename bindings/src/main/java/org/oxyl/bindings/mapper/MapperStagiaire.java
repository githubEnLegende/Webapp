package org.oxyl.bindings.mapper;

import org.oxyl.core.model.Promotion;
import org.oxyl.core.model.Stagiaire;
import org.oxyl.bindings.dto.stagiairedto.StagiaireDTOEditAdd;
import org.oxyl.bindings.dto.stagiairedto.StagiaireDTOPage;
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

//    public Optional<Stagiaire> rsToStagiaire(ResultSet rs) {
//        try {
//
//            Promotion promotion = new Promotion.PromotionBuilder(rs.getInt("promotion_id"), rs.getString("name")).build();
//
//            return Optional.of(new Stagiaire.StagiaireBuilder(rs.getInt("id"),
//                    rs.getString("first_name"),
//                    rs.getString("last_name"),
//                    rs.getTimestamp("arrival")
//                            == null ? null : rs.getTimestamp("arrival").toLocalDateTime().toLocalDate())
//                    .formationOver(rs.getTimestamp("formation_over")
//                            == null ? null : rs.getTimestamp("formation_over").toLocalDateTime().toLocalDate())
//                    .promotion(promotion)
//                    .build());
//        } catch (SQLException e) {
//            logger.error("Erreur Base de donnée", e);
//            return Optional.empty();
//        }
//    }

    public Stagiaire dtoToModel(StagiaireDTOEditAdd stagiaire) {
        Promotion promotion = new Promotion.PromotionBuilder(
                Integer.parseInt(stagiaire.promotionId()), stagiaire.promotionName())
                .build();
        return new Stagiaire.StagiaireBuilder()
                .firstName(stagiaire.prenom())
                .lastName(stagiaire.nom())
                .arrival(mapperDate.stringtoLocalDate(stagiaire.arrival()))
                .formationOver(mapperDate.stringtoLocalDate(stagiaire.finFormation()))
                .promotion(promotion)
                .build();
    }

    public StagiaireDTOEditAdd modelToDtoEditAdd(Stagiaire stagiaire) {
        return new StagiaireDTOEditAdd(
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

    public Stagiaire dtoPagetoModel(StagiaireDTOPage stagiaireDTOPage){
        return new Stagiaire.StagiaireBuilder()
                .id(stagiaireDTOPage.id())
                .firstName(stagiaireDTOPage.prenom())
                .lastName(stagiaireDTOPage.nom())
                .arrival(mapperDate.stringtoLocalDate(stagiaireDTOPage.arrival()))
                .formationOver(mapperDate.stringtoLocalDate(stagiaireDTOPage.finFormation())).build();
    }
    
    public List<StagiaireDTOPage> listModelToListDtoPage(List<Stagiaire> stagiaires) {
        return stagiaires.stream().map(this::modelToDtoPage).toList();
    }

//    @Override
//    public Stagiaire mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Optional<Stagiaire> stagiaire = rsToStagiaire(rs);
//        return stagiaire.orElse(null);
//    }
}
