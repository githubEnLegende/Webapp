package org.oxyl.persistence;

import org.oxyl.mapper.MapperQuestion;
import org.oxyl.model.Question;
import org.oxyl.model.Reponse;
import org.oxyl.mapper.MapperReponse;
import org.oxyl.persistence.jdbcconfig.JdbcTemplateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class QuestionDAO {

    private static final Logger logger = LoggerFactory.getLogger(QuestionDAO.class);

    private final MapperReponse mapperReponse;
    private final MapperQuestion mapperQuestion;
    private final JdbcTemplate jdbcTemplate;

    public QuestionDAO(MapperQuestion mapperQuestion, JdbcTemplateConfig jdbcTemplate, MapperReponse mapperReponse) {
        this.mapperQuestion = mapperQuestion;
        this.mapperReponse = mapperReponse;
        this.jdbcTemplate = jdbcTemplate.jdbcTemplate();
    }

    public void getQuestionById(int questionId) {
        try {
            String sql = "SELECT id, title, statement, chapter_id FROM question WHERE id = ?";
            Question question = jdbcTemplate.queryForObject(sql, mapperQuestion, questionId);

            if (question != null) {
                System.out.print(question);

                String answer = "SELECT id, text, valid_answer, question_id FROM answer WHERE question_id = ?";
                List<Reponse> reponses = jdbcTemplate.query(answer, mapperReponse, question.getId());

                for (Reponse reponse : reponses) {
                    System.out.println(reponse);
                }
            } else {
                System.out.println("Aucun résultat trouvé.");
            }
        } catch(DataAccessException e){
            logger.error("Erreur lors du get question by Id", e);
        }
    }

    @Transactional
    public void deleteQuestion(int id) {
        String answerQuery = "DELETE FROM answer WHERE question_id = ?";
        String questionQuery = "DELETE FROM question WHERE id = ?";

        try {
            jdbcTemplate.update(answerQuery, id);
            jdbcTemplate.update(questionQuery, id);

            System.out.println("Question supprimée avec succès");
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la suppression de la question", e);
        }
    }

    public Optional<List<Question>> getAllQuestion() {
        String sql = "SELECT id, title, statement, chapter_id FROM question";
        try {
            List<Question> questions = jdbcTemplate.query(sql, mapperQuestion);
            return Optional.of(questions);
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération des stagiaires.", e);
            return Optional.empty();
        }
    }

    public List<String> getQuestionAnswer(int id) {
        String sql = "SELECT question.id, title, statement, chapter_id, answer.text "
                + "FROM question LEFT JOIN answer ON answer.question_id = question.id WHERE question.id = ?";

        List<String> result = new ArrayList<>();
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id);

            for (Map<String, Object> row : rows) {
                if (result.isEmpty()) {
                    result.add(row.get("title").toString());
                    result.add(row.get("statement").toString());
                    result.add(row.get("chapter_id").toString());
                }
                result.add(row.get("text").toString());
            }
            return result;
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération de la question et des réponses", e);
            return null;
        }
    }
}
