package org.oxyl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.oxyl.model.Promotion;
import org.oxyl.newro.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.Option;


public class MapperPromotion {

    private static MapperPromotion instance;
    private static Logger logger = LoggerFactory.getLogger(MapperPromotion.class);


    public static MapperPromotion getInstance() {
        if (instance == null) {
            instance = new MapperPromotion();
        }
        return instance;
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
}
