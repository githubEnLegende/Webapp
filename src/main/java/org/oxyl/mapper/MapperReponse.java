package org.oxyl.mapper;

import org.oxyl.model.Reponse;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperReponse {

    public Reponse rsToReponse(ResultSet rs) {
        try {
            return new Reponse.ReponseBuilder(rs.getInt("id"),
                    rs.getString("text"),
                    rs.getInt("valid_answer"),
                    rs.getInt("question_id")).build();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
