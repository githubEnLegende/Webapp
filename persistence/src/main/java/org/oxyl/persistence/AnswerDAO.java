package org.oxyl.persistence;

import org.oxyl.core.model.Reponse;
import org.oxyl.persistence.entitymapper.AnswerEntityMapper;
import org.oxyl.persistence.repository.AnswerRepository;
import org.springframework.stereotype.Component;

@Component
public class AnswerDAO {

    private final AnswerRepository answerRepository;
    private final AnswerEntityMapper answerEntityMapper;

    public AnswerDAO(AnswerRepository answerRepository, AnswerEntityMapper answerEntityMapper) {
        this.answerRepository = answerRepository;
        this.answerEntityMapper = answerEntityMapper;
    }

    public void createAnswer(Reponse reponse) {
        answerRepository.save(
                answerEntityMapper.toEntity(reponse)
        );
    }
}
