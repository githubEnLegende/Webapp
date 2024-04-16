package model;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Reponse {
	private int id;
	private String texte;
	private int valid;
	private int questionId;
	
	public Reponse(ResultSet rs) {
		try {
			this.id = rs.getInt("id");
			this.texte = rs.getString("text");
			this.valid = rs.getInt("valid_answer");
			this.questionId = rs.getInt("question_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public String toString() {
        return "Reponse{" +
                "id=" + id +
                ", contenu='" + texte + '\'' +
                ", validité='" + valid + '\'' +
                ", chapterId=" + questionId +
                '}';
    }
	
}
