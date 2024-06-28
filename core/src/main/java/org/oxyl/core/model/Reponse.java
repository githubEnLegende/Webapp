package org.oxyl.core.model;

public class Reponse {
    private long id;
    private String label;
    private String texte;
    private long valid;
    private long questionId;


    public Reponse(ReponseBuilder builder) {
        super();
        this.id = builder.id;
        this.label = builder.label;
        this.texte = builder.texte;
        this.valid = builder.valid;
        this.questionId = builder.questionId;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public long getValid() {
        return valid;
    }

    public void setValid(long valid) {
        this.valid = valid;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getLabel() {
        return label;
    }

    public String toString() {
        return "Reponse{" +
                "id=" + id +
                ", contenu='" + texte + '\'' +
                ", validité='" + valid + '\'' +
                ", chapterId=" + questionId +
                '}';
    }

    public static class ReponseBuilder {
        private long id;
        private String label;
        private String texte;
        private long valid;
        private long questionId;


        public ReponseBuilder() {
        }

        public ReponseBuilder id(long id) {
            this.id = id;
            return this;
        }

        public ReponseBuilder label(String label) {
            this.label = label;
            return this;
        }

        public ReponseBuilder texte(String texte) {
            this.texte = texte;
            return this;
        }

        public ReponseBuilder valid(long valid) {
            this.valid = valid;
            return this;
        }

        public ReponseBuilder questionId(long questionId) {
            this.questionId = questionId;
            return this;
        }

        public Reponse build() {
            return new Reponse(this);
        }


    }

}
