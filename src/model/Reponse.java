package model;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Reponse {
	private int id;
	private String texte;
	private int valid;
	private int questionId;
	
	
	
	public Reponse(ReponseBuilder builder) {
		super();
		this.id = builder.id;
		this.texte = builder.texte;
		this.valid = builder.valid;
		this.questionId = builder.questionId;
	}


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
	
	public static class ReponseBuilder{
		private int id;
		private String texte;
		private int valid;
		private int questionId;
		
		
		public ReponseBuilder(int id, String texte, int valid, int questionId) {
			super();
			this.id = id;
			this.texte = texte;
			this.valid = valid;
			this.questionId = questionId;
		}
		
		public ReponseBuilder id(int id) {
			this.id = id;
			return this;
		}
		
		public ReponseBuilder texte(String texte) {
			this.texte = texte;
			return this;
		}
		
		public ReponseBuilder valid(int valid) {
			this.valid = valid;
			return this;
		}
		
		public ReponseBuilder questionId(int questionId) {
			this.questionId = questionId;
			return this;
		}
		
		public Reponse build() {
			return new Reponse(this);
		}
		
		
	}
	
}
