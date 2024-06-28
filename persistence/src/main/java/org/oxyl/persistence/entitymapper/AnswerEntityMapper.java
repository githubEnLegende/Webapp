package org.oxyl.persistence.entitymapper;

import org.oxyl.core.model.Reponse;
import org.oxyl.persistence.entities.AnswerEntity;
import org.springframework.stereotype.Component;

@Component
public class AnswerEntityMapper {

    public Reponse toModel(AnswerEntity answerEntity) {
        return new Reponse.ReponseBuilder()
                .label(answerEntity.getLabel())
                .texte(answerEntity.getText())
                .valid(answerEntity.getValidAnswer())
                .build();
    }
}
