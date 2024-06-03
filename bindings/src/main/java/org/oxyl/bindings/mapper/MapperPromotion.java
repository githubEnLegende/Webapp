package org.oxyl.bindings.mapper;

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

    public MapperPromotion() {
    }


    public Optional<Promotion> rsToPromotion(ResultSet rs) {
        try {
            return Optional.of(new Promotion.PromotionBuilder(rs.getInt("id"),
                    rs.getString("name")).build());
        } catch (SQLException e) {
            logger.error("Erreur Base de donnée", e.getMessage());
            return Optional.empty();
        }
    }

    public Promotion stringToPromotion(String promotion) {
        String[] parts = promotion.split(",");

        // La première partie est entre crochets, donc nous devons supprimer les crochets
        String id = parts[0].replace("[", "");

        // La deuxième partie est le nom de la promotion, donc nous pouvons l'utiliser telle quelle
        String promotionName = parts[1].replace("]", "").trim();
        ;

        return new Promotion.PromotionBuilder(Integer.parseInt(id), promotionName).build();
    }

    @Override
    public Promotion mapRow(ResultSet rs, int rowNum) throws SQLException {
        Optional<Promotion> promotion = rsToPromotion(rs);
        return promotion.orElse(null);
    }

}
