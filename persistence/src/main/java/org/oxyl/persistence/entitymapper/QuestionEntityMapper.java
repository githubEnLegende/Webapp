package org.oxyl.persistence.entitymapper;

import org.oxyl.core.model.Question;
import org.oxyl.persistence.entities.QuestionEntity;
import org.springframework.stereotype.Component;

@Component
public class QuestionEntityMapper {

    private final ChapitreEntityMapper chapterEntityMapper;

    public QuestionEntityMapper(ChapitreEntityMapper chapterEntityMapper) {
        this.chapterEntityMapper = chapterEntityMapper;
    }

    public Question toModel(QuestionEntity questionEntity) {
        return new Question.QuestionBuilder(
                questionEntity.getId(),
                questionEntity.getTitle(),
                questionEntity.getStatement(),
                chapterEntityMapper.toModel(questionEntity.getChapter())
        ).build();
    }
}
