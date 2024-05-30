package org.oxyl.webapp.mapper;

import org.oxyl.core.model.Reponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class MapperReponse implements RowMapper<Reponse> {

    private static final Logger logger = LoggerFactory.getLogger(MapperReponse.class);

    public MapperReponse() {
    }

    public Optional<Reponse> rsToReponse(ResultSet rs) {
        try {
            return Optional.of(new Reponse.ReponseBuilder(rs.getInt("id"),
                    rs.getString("text"),
                    rs.getInt("valid_answer"),
                    rs.getInt("question_id")).build());

        } catch (SQLException e) {
            logger.error("Erreur Base de donnée", e);
            return Optional.empty();
        }
    }

    @Override
    public Reponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        Optional<Reponse> rsToReponse = rsToReponse(rs);
        return rsToReponse.orElse(null);
    }
}
