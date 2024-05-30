package org.oxyl.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "chapter")
public class ChapterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "parent_path", nullable = false)
    private String parentPath;

    public ChapterEntity() {
    }

    public ChapterEntity(int id, String name, String parentPath) {
        this.id = id;
        this.name = name;
        this.parentPath = parentPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }
}
