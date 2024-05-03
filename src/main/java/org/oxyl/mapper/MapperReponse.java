package org.oxyl.mapper;

import org.oxyl.model.Reponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MapperReponse {

    private static MapperReponse instance;
    private static final Logger logger = LoggerFactory.getLogger(MapperReponse.class);

    public static MapperReponse getInstance() {
        if (instance == null) {
            instance = new MapperReponse();
        }
        return instance;
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
}
