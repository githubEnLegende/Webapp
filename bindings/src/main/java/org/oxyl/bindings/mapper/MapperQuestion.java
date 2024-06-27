package org.oxyl.bindings.mapper;

import org.oxyl.bindings.dto.questiondto.QuestionPageDTO;
import org.oxyl.core.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class MapperQuestion {

    private final static Logger logger = LoggerFactory.getLogger(MapperChapitre.class);

    public MapperQuestion() {
    }

    public QuestionPageDTO convertToQuestionPageDTO(Question question) {
        return new QuestionPageDTO(
                question.getId(),
                question.getTitle(),
                question.getStatement(),
                question.getChapitre().getName()
        );
    }

}
