package org.oxyl.bindings.mapper;

import org.oxyl.bindings.dto.PromotionDTO;
import org.oxyl.core.model.Promotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class MapperPromotion implements RowMapper<Promotion> {

    private static Logger logger = LoggerFactory.getLogger(MapperPromotion.class);

    public MapperPromotion() {}


    public Optional<Promotion> rsToPromotion(ResultSet rs) {
        try {
            return Optional.of(new Promotion.PromotionBuilder(rs.getInt("id"),
                    rs.getString("name")).build());
        } catch (SQLException e) {
            logger.error("Erreur Base de donnée", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Promotion mapRow(ResultSet rs, int rowNum) throws SQLException {
        Optional<Promotion> promotion = rsToPromotion(rs);
        return promotion.orElse(null);
    }

    public PromotionDTO convertToPromotionDTO(Promotion promotion) {
        return new PromotionDTO(
                promotion.getId(),
                promotion.getName()
        );
    }

}
