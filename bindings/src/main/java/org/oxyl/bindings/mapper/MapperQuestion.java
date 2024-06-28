package org.oxyl.bindings.mapper;

import org.oxyl.bindings.dto.questiondto.QuestionPageDTO;
import org.oxyl.bindings.dto.questiondto.QuestionQuizDTO;
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
    private final MapperReponse mapperReponse;

    public MapperQuestion(MapperReponse mapperReponse) {
        this.mapperReponse = mapperReponse;
    }

    public QuestionPageDTO convertToQuestionPageDTO(Question question) {
        return new QuestionPageDTO(
                question.getId(),
                question.getTitle(),
                question.getStatement(),
                question.getChapitre().getName()
        );
    }

    public QuestionQuizDTO convertToQuestionQuizDTO(Question question) {
        return new QuestionQuizDTO(
                question.getTitle(),
                question.getStatement(),
                question.getAnswerList()
                        .stream()
                        .map(mapperReponse::convertToAnswerDTO)
                        .toList()
        );
    }
}
