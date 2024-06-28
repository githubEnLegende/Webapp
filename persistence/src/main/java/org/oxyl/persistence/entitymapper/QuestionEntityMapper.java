package org.oxyl.persistence.entitymapper;

import org.oxyl.core.model.Question;
import org.oxyl.persistence.entities.QuestionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class QuestionEntityMapper {

    private final ChapitreEntityMapper chapterEntityMapper;
    private final AnswerEntityMapper answerEntityMapper;

    public QuestionEntityMapper(ChapitreEntityMapper chapterEntityMapper, AnswerEntityMapper answerEntityMapper) {
        this.chapterEntityMapper = chapterEntityMapper;
        this.answerEntityMapper = answerEntityMapper;
    }

    public Question toModel(QuestionEntity questionEntity) {
        return new Question.QuestionBuilder(
                questionEntity.getId(),
                questionEntity.getTitle(),
                questionEntity.getStatement(),
                chapterEntityMapper.toModel(questionEntity.getChapter()),
                questionEntity.getAnswers()
                        .stream()
                        .map(answerEntityMapper::toModel)
                        .toList()
        ).build();
    }
}
