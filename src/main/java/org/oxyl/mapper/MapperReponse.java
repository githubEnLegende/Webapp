package org.oxyl.mapper;

import org.oxyl.model.Reponse;

import javax.swing.text.html.Option;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MapperReponse {

    public Optional<Reponse> rsToReponse(ResultSet rs) {
        try {
            return Optional.of(new Reponse.ReponseBuilder(rs.getInt("id"),
                    rs.getString("text"),
                    rs.getInt("valid_answer"),
                    rs.getInt("question_id")).build());

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
