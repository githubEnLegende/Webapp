package org.oxyl.webapp.mapper;

import org.oxyl.core.model.Promotion;
import org.oxyl.core.model.Stagiaire;
import org.oxyl.webapp.dto.StagiaireDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


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

    public Stagiaire dtoToModel(StagiaireDTO stagiaire) {
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

//    @Override
//    public Stagiaire mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Optional<Stagiaire> stagiaire = rsToStagiaire(rs);
//        return stagiaire.orElse(null);
//    }
}
