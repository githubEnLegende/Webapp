package org.oxyl.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "statement")
    @NotNull
    private String statement;

    @Column(name = "chapter_id")
    @NotNull
    private int chapterId;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<AnswerEntity> answers;

    public QuestionEntity() {
    }

    public QuestionEntity(int id, String title, String statement, int chapterId) {
        this.id = id;
        this.title = title;
        this.statement = statement;
        this.chapterId = chapterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }
}
