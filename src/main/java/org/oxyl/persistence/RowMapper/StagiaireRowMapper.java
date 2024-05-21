package org.oxyl.persistence.RowMapper;

import org.oxyl.model.Promotion;
import org.springframework.jdbc.core.RowMapper;
import org.oxyl.model.Stagiaire;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StagiaireRowMapper implements RowMapper<Stagiaire> {

    @Override
    public Stagiaire mapRow(ResultSet rs, int rowNum) throws SQLException {

        Promotion promotion = new Promotion.PromotionBuilder(rs.getInt("promotion_id"), rs.getString("name")).build();

        Stagiaire stagiaire = new Stagiaire.StagiaireBuilder(rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getTimestamp("arrival") == null ? null : rs.getTimestamp("arrival").toLocalDateTime().toLocalDate())
                .formationOver(rs.getTimestamp("formation_over") == null ? null : rs.getTimestamp("formation_over").toLocalDateTime().toLocalDate())
                .promotion(promotion)
                .build();

        return stagiaire;
    }

}
