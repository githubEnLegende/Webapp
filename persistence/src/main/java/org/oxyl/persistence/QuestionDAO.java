package org.oxyl.persistence;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.oxyl.core.model.Question;
import org.oxyl.persistence.entities.QuestionEntity;
import org.oxyl.persistence.entitymapper.QuestionEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class QuestionDAO {

    private static final Logger logger = LoggerFactory.getLogger(QuestionDAO.class);

    private final QuestionEntityMapper mapperQuestion;
    private final Session session;

    public QuestionDAO(QuestionEntityMapper mapperQuestion, Session session) {
        this.mapperQuestion = mapperQuestion;
        this.session = session;
    }

    public Optional<Question> getQuestionById(int questionId) {
        QuestionEntity questionEntity = session.get(QuestionEntity.class, questionId);
        if (questionEntity != null) {
            return Optional.of(mapperQuestion.toModel(questionEntity));
        }
        return Optional.empty();
    }

    public void deleteQuestion(int id) {
        QuestionEntity questionEntity = session.get(QuestionEntity.class, id);
        if (questionEntity != null) {
            session.remove(questionEntity);
            logger.info("Question with ID: {} deleted successfully", id);
        } else {
            logger.info("No question found with ID: {}", id);
        }
    }


    public List<Question> getAllQuestion() {
        Query<QuestionEntity> query = session.createQuery("from QuestionEntity", QuestionEntity.class);
        return query.list().stream().map(mapperQuestion::toModel).toList();
    }

    @Transactional
    public List<String> getQuestionAnswer(long id) {
        String hql = "SELECT q FROM QuestionEntity q LEFT JOIN FETCH q.answers WHERE q.id = :id";
        Query<QuestionEntity> query = session.createQuery(hql, QuestionEntity.class);
        query.setParameter("id", id);

        QuestionEntity questionEntity = query.uniqueResult();
        List<String> response = new ArrayList<>();

        if (questionEntity != null) {
            response.add(questionEntity.getTitle());
            response.add(questionEntity.getStatement());
            response.add(String.valueOf(questionEntity.getChapterId()));

            questionEntity.getAnswers().forEach(answer -> response.add(answer.getText()));
        }

        return response;
    }
}
