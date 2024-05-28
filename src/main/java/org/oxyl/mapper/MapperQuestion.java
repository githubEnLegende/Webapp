package org.oxyl.mapper;

import org.oxyl.model.Chapitre;
import org.oxyl.model.Question;
import org.oxyl.persistence.entities.QuestionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class MapperQuestion implements RowMapper<Question> {

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
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Optional<Question> question = rsToQuestion(rs);
        return question.orElse(null);
    }

    public Question toModel(QuestionEntity questionEntity) {
        return new Question.QuestionBuilder(
                questionEntity.getId(),
                questionEntity.getTitle(),
                questionEntity.getStatement(),
                questionEntity.getChapterId()
        ).build();
    }

}
