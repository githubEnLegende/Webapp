package org.oxyl.bindings.mapper;

import org.oxyl.bindings.dto.answersDTO.AnswerDTO;
import org.oxyl.core.model.Reponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class MapperReponse {

    private static final Logger logger = LoggerFactory.getLogger(MapperReponse.class);

    public MapperReponse() {
    }

    public AnswerDTO convertToAnswerDTO(Reponse reponse) {
        return new AnswerDTO(
                reponse.getLabel(),
                reponse.getTexte(),
                reponse.getValid()
        );
    }

    public Reponse convertToModel(AnswerDTO answerDTO) {
        return new Reponse.ReponseBuilder()
                .label(answerDTO.label())
                .texte(answerDTO.text())
                .valid(answerDTO.validAnswer())
                .build();
    }
}
