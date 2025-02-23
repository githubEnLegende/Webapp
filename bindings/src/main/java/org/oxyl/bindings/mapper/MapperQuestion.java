package org.oxyl.bindings.mapper;

import org.oxyl.bindings.dto.questiondto.*;
import org.oxyl.core.model.Chapitre;
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
    private final MapperChapitre mapperChapitre;

    public MapperQuestion(MapperReponse mapperReponse, MapperChapitre mapperChapitre) {
        this.mapperReponse = mapperReponse;
        this.mapperChapitre = mapperChapitre;
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

    public Question convertToModel(QuestionAddDTO questionAddDTO) {
        return new Question.QuestionBuilder()
                .title(questionAddDTO.title())
                .statement(questionAddDTO.statement())
                .chapitre(new Chapitre.ChapitreBuilder()
                        .id(questionAddDTO.chapterId())
                        .build())
                .answers(questionAddDTO.answers()
                        .stream()
                        .map(mapperReponse::convertToModel)
                        .toList())
                .build();
    }

    public Question convertToModel(QuestionEditDTO questionEditDTO) {
        return new Question.QuestionBuilder()
                .id(questionEditDTO.id())
                .title(questionEditDTO.title())
                .statement(questionEditDTO.statement())
                .chapitre(new Chapitre.ChapitreBuilder()
                        .id(questionEditDTO.chapterId())
                        .build())
                .answers(questionEditDTO.answers()
                        .stream()
                        .map(mapperReponse::convertToModel)
                        .toList())
                .build();
    }

    public QuestionInformationDTO convertToQuestionInformationDTO(Question question) {
        return new QuestionInformationDTO(
                question.getId(),
                question.getTitle(),
                question.getStatement(),
                mapperChapitre.convertToChapterDTO(question.getChapitre()),
                question.getAnswerList()
                        .stream()
                        .map(mapperReponse::convertToAnswerDTO)
                        .toList()
        );
    }
}
