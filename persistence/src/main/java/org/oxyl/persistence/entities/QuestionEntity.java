package org.oxyl.persistence.entities;


import jakarta.persistence.*;
import org.oxyl.core.model.Chapitre;

import java.util.List;

@Entity
@Table(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "statement", length = 10000)
    private String statement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
    private ChapterEntity chapter;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<AnswerEntity> answers;

    public QuestionEntity() {
    }

    public QuestionEntity(int id, String title, String statement, ChapterEntity chapitre) {
        this.id = id;
        this.title = title;
        this.statement = statement;
        this.chapter = chapitre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ChapterEntity getChapter() {
        return chapter;
    }

    public void setChapterId(ChapterEntity chapter) {
        this.chapter = chapter;
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
