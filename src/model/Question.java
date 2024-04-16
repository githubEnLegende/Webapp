package model;

public class Question {
	
	private int id;
	private String title;
	private String statement;
	private int chapitreId;
	
	public Question(int id, String title, String statement, int chapitre) {
		super();
		this.id = id;
		this.title = title;
		this.statement = statement;
		this.chapitreId = chapitre;
	}
	
	public Question() {
		super();
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

}
