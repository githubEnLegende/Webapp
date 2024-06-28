package org.oxyl.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.oxyl.core.model.Question;
import org.oxyl.persistence.entities.AnswerEntity;
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

    @Transactional
    public void deleteQuestion(long id) {

        try {
            Transaction transaction = session.beginTransaction();
            Query<AnswerEntity> queryAnswer = session.createQuery("delete from AnswerEntity where question.id = :id", null);
            Query<QuestionEntity> queryQuestion = session.createQuery("delete from QuestionEntity where id = :id", null);

            int res = queryAnswer.setParameter("id", id).executeUpdate() + queryQuestion.setParameter("id", id).executeUpdate();
            if (res > 0) {
                logger.info("Question with ID: {} deleted successfully", id);
            } else {
                logger.info("No question found with ID: {}", id);
            }
            transaction.commit();
        } catch (Exception e) {
            logger.error("Error while deleting question with ID: {}", id, e);
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
            response.add(String.valueOf(questionEntity.getChapter()));

            questionEntity.getAnswers().forEach(answer -> response.add(answer.getText()));
        }

        return response;
    }

    public List<Question> getQuestionOfChapter(int chapterId, int number) {
        String hql =
                """
                    SELECT q
                    FROM QuestionEntity q
                    LEFT JOIN FETCH q.answers
                    WHERE q.chapter.id = :id
                    ORDER BY RAND()
                """;
        Query<QuestionEntity> query = session.createQuery(hql, QuestionEntity.class);

        query.setParameter("id", chapterId);
        query.setMaxResults(number);

        return query.list().stream().map(mapperQuestion::toModel).toList();
    }
}
