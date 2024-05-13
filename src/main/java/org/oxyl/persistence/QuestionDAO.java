package org.oxyl.persistence;

import org.oxyl.model.Question;
import org.oxyl.model.Reponse;
import org.oxyl.mapper.MapperReponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDAO {

    private static Logger logger = LoggerFactory.getLogger(QuestionDAO.class);
    private static QuestionDAO instance;

    public static QuestionDAO getInstance() {
        if (instance == null) {
            instance = new QuestionDAO();
        }
        return instance;
    }

    public void getQuestionById(int questionId) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try (Connection conn = DataSource.getConnection();) {
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
                    Reponse reponse = MapperReponse.getInstance().rsToReponse(rs).get();
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
        try (Connection conn = DataSource.getConnection();
             PreparedStatement answStmt = conn.prepareStatement(answerQuery);
             PreparedStatement questStmt = conn.prepareStatement(questionQuery)) {

            conn.setAutoCommit(false);

            answStmt.setInt(1, id);
            questStmt.setInt(1, id);

            answStmt.execute();
            questStmt.execute();

            conn.commit();

        } catch (SQLException e) {
            logger.error("Problème lors de l'accès à la bdd", e);
            System.out.println("Erreur lors de la supression question");
        }
    }
}
