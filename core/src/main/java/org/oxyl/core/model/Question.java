package org.oxyl.core.model;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private long id;
    private String title;
    private String statement;
    private List<Reponse> answerList;
    private Chapitre chapitre;

    public Question(QuestionBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.statement = builder.statement;
        this.answerList = builder.answerList;
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

    public List<Reponse> getAnswerList() {
        return answerList;
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
        private List<Reponse> answerList = new ArrayList<>();

        public QuestionBuilder() {
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

        public QuestionBuilder chapitre(Chapitre chapitre) {
            this.chapitre = chapitre;
            return this;
        }

        public QuestionBuilder answers(List<Reponse> answers) {
            this.answerList = answers;
            return this;
        }


        public Question build() {
            return new Question(this);
        }


    }

}
