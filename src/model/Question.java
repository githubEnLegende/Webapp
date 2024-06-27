package model;

public class Question {
	
	private int id;
	private String title;
	private String statement;
	private int chapitreId;
	
	public Question(QuestionBuilder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.statement = builder.statement;
		this.chapitreId = builder.chapitreId;
	}
	
	public Question() {
		this.id = 0;
		this.title = null;
		this.statement = null;
		this.chapitreId = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public int getChapitreId() {
		return chapitreId;
	}

	public void setChapitreId(int i) {
		this.chapitreId = i;
	}

	public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", statement='" + statement + '\'' +
                ", chapterId=" + chapitreId +
                "}\n";
    }
	
	public static class QuestionBuilder{
		private int id;
		private String title;
		private String statement;
		private int chapitreId;

		public QuestionBuilder(int id, String title, String statement, int chapitreId) {
			this.id = id;
			this.title = title;
			this.statement = statement;
			this.chapitreId = chapitreId;
		}

		public QuestionBuilder id(int id) {
			this.id = id;
			return this;
		}
		
		public QuestionBuilder title(String title) {
			this.title = title;
			return this;
		}
		
		public QuestionBuilder statement(String statement) {
			this.statement = statement;
			return this;
		}
		
		public QuestionBuilder chapitreId(int chapitreId) {
			this.chapitreId = chapitreId;
			return this;
		}
		
		public Question build() {
			return new Question(this);
		}
		
		
	}

}
