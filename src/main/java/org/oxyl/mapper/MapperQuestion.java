package org.oxyl.mapper;

import org.oxyl.model.Chapitre;
import org.oxyl.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class MapperQuestion {

    private final static Logger logger = LoggerFactory.getLogger(MapperChapitre.class);

    public MapperQuestion() {}

    public Optional<Question> rsToQuestion(ResultSet rs)  {
        try{
            return Optional.of(new Question.QuestionBuilder(rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("statement"),
                    rs.getInt("chapter_id")).build());
        } catch (SQLException e) {
            logger.error("Erreur lors du passage de rs à Chapitre", e);
            return Optional.empty();
        }
    }

}
