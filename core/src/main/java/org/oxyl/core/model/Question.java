package org.oxyl.core.model;

public class Question {

    private long id;
    private String title;
    private String statement;
    private Chapitre chapitre;

    public Question(QuestionBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.statement = builder.statement;
        this.chapitre = builder.chapitre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Chapitre getChapitre() {
        return chapitre;
    }

    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
    }

    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", statement='" + statement + '\'' +
                ", chapter=" + chapitre +
                "}\n";
    }

    public static class QuestionBuilder {
        private long id;
        private String title;
        private String statement;
        private Chapitre chapitre;

        public QuestionBuilder(long id, String title, String statement, Chapitre chapitre) {
            this.id = id;
            this.title = title;
            this.statement = statement;
            this.chapitre = chapitre;
        }

        public QuestionBuilder id(long id) {
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

        public QuestionBuilder chapitreId(Chapitre chapitre) {
            this.chapitre = chapitre;
            return this;
        }

        public Question build() {
            return new Question(this);
        }


    }

}
