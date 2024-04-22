package org.oxyl.persistence;

import org.oxyl.model.Question;
import org.oxyl.model.Reponse;
import org.oxyl.mapper.MapperReponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDAO {

    public static void getQuestionById(int questionId) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try(Connection conn = MySqlConnexion.getInstance().getConnection();) {
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

                while(rs.next()) {
                    Reponse reponse = new MapperReponse().rsToReponse(rs).get();
                    System.out.println(reponse.toString());
                }
            } else {
                System.out.println("Aucun résultat trouvé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
