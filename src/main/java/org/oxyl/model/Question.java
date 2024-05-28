package org.oxyl.model;

public class Question {

    private long id;
    private String title;
    private String statement;
    private long chapitreId;

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

    public long getChapitreId() {
        return chapitreId;
    }

    public void setChapitreId(long i) {
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

    public static class QuestionBuilder {
        private long id;
        private String title;
        private String statement;
        private long chapitreId;

        public QuestionBuilder(long id, String title, String statement, long chapitreId) {
            this.id = id;
            this.title = title;
            this.statement = statement;
            this.chapitreId = chapitreId;
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

        public QuestionBuilder chapitreId(long chapitreId) {
            this.chapitreId = chapitreId;
            return this;
        }

        public Question build() {
            return new Question(this);
        }


    }

}
