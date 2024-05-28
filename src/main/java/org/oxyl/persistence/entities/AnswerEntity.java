package org.oxyl.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "answer")
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "label")
    @NotNull
    private String label;

    @Column(name = "text")
    @NotNull
    private String text;

    @Column(name = "valid_answer")
    @NotNull
    private int validAnswer;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    public AnswerEntity() {
    }

    public AnswerEntity(int id, String label, String text, int validAnswer) {
        this.id = id;
        this.label = label;
        this.text = text;
        this.validAnswer = validAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValidAnswer() {
        return validAnswer;
    }

    public void setValidAnswer(int validAnswer) {
        this.validAnswer = validAnswer;
    }

//    public int getQuestionId() {
//        return questionId;
//    }
//
//    public void setQuestionId(int questionId) {
//        this.questionId = questionId;
//    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }
}
