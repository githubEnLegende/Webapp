package org.oxyl.persistence;

import com.zaxxer.hikari.HikariDataSource;
import org.oxyl.mapper.MapperQuestion;
import org.oxyl.model.Chapitre;
import org.oxyl.model.Question;
import org.oxyl.model.Reponse;
import org.oxyl.mapper.MapperReponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class QuestionDAO {

    private static Logger logger = LoggerFactory.getLogger(QuestionDAO.class);
    private MapperReponse mapperReponse;

    private final HikariDataSource dataSource;
    private MapperQuestion mapperQuestion;

    public QuestionDAO(HikariDataSource dataSource, MapperQuestion mapperQuestion) {
        this.dataSource = dataSource;
        this.mapperQuestion = mapperQuestion;
    }

    public void getQuestionById(int questionId) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try (Connection conn = dataSource.getConnection();) {
            String sql = "SELECT id, title, statement, chapter_id FROM question WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, questionId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Question question = new Question.QuestionBuilder(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("statement"),
                        rs.getInt("chapter_id")).build();
                System.out.print(question.toString());

                String answer = "SELECT id, text, valid_answer, question_id FROM answer WHERE question_id = ?";
                stmt = conn.prepareStatement(answer);
                stmt.setInt(1, question.getId());
                rs = stmt.executeQuery();

                while (rs.next()) {
                    Reponse reponse = mapperReponse.rsToReponse(rs).get();
                    System.out.println(reponse.toString());
                }
            } else {
                System.out.println("Aucun résultat trouvé.");
            }
        } catch (SQLException e) {
            logger.error("Erreur lors de l'affichage des questions", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                logger.error("Erreur lors de la fermeture des statements et result sets", e);
            }
        }
    }

    public void deleteQuestion(int id) {
        String answerQuery = "DELETE FROM answer WHERE question_id = ?";
        String questionQuery = "DELETE FROM question WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement answStmt = conn.prepareStatement(answerQuery);
             PreparedStatement questStmt = conn.prepareStatement(questionQuery)) {

            conn.setAutoCommit(false);

            answStmt.setInt(1, id);
            questStmt.setInt(1, id);

            answStmt.execute();
            questStmt.execute();
            try {
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Erreur lors du commit lors de la délétion des questions");
                logger.error("Erreur lors du commit lors de la délétion des questions", e);
            }

            System.out.println("Question suprimée");
        } catch (SQLException e) {
            logger.error("Problème lors de l'accès à la bdd", e);
            System.out.println("Erreur de connexion lors de la supression question");
        }
    }

    public Optional<List<Question>> getAllQuestion() {
        String sql = "SELECT id, title, statement, chapter_id FROM question";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            List<Question> questions = new ArrayList<>();

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                questions.add(mapperQuestion.rsToQuestion(rs).get());
            }
            return Optional.of(questions);
        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération des stagiaires.", e);
            return Optional.empty();
        }
    }

    public List<String> getQuestionAnswer(int id) {
        String sql = "SELECT question.id, title, statement, chapter_id, answer.text" +
                " FROM question LEFT JOIN answer ON answer.question_id = question.id WHERE question.id = ?";

        List<String> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            result.add(rs.getString("title"));
            result.add(rs.getString("statement"));
            result.add(rs.getString("chapter_id"));
            do {
                result.add(rs.getString("text"));
            } while (rs.next());
            return result;

        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération de la question et des réponses", e);
            return null;
        }
    }
}
