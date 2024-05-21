package org.oxyl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.oxyl.model.Promotion;
import org.oxyl.model.Stagiaire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperStagiaire implements RowMapper<Stagiaire> {

    private static final Logger logger = LoggerFactory.getLogger(MapperStagiaire.class);

    public MapperStagiaire() {
    }

    public Optional<Stagiaire> rsToStagiaire(ResultSet rs) {
        try {

            Promotion promotion = new Promotion.PromotionBuilder(rs.getInt("promotion_id"), rs.getString("name")).build();

            return Optional.of(new Stagiaire.StagiaireBuilder(rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getTimestamp("arrival")
                            == null ? null : rs.getTimestamp("arrival").toLocalDateTime().toLocalDate())
                    .formationOver(rs.getTimestamp("formation_over")
                            == null ? null : rs.getTimestamp("formation_over").toLocalDateTime().toLocalDate())
                    .promotion(promotion)
                    .build());
        } catch (SQLException e) {
            logger.error("Erreur Base de donnée", e);
            return Optional.empty();
        }
    }

    @Override
    public Stagiaire mapRow(ResultSet rs, int rowNum) throws SQLException {
        Optional<Stagiaire> stagiaire = rsToStagiaire(rs);
        return stagiaire.orElse(null);
    }
}
