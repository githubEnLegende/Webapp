package org.oxyl.mapper;

import org.oxyl.model.Reponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.Option;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MapperReponse {

    private static Logger logger = LoggerFactory.getLogger(MapperReponse.class);

    public Optional<Reponse> rsToReponse(ResultSet rs) {
        try {
            return Optional.of(new Reponse.ReponseBuilder(rs.getInt("id"),
                    rs.getString("text"),
                    rs.getInt("valid_answer"),
                    rs.getInt("question_id")).build());

        } catch (SQLException e) {
            logger.error("Erreur Base de donnée", e.getMessage());
            return Optional.empty();
        }
    }
}
