package org.oxyl.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "promotion")
public class PromotionEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public PromotionEntity() {
    }

    public PromotionEntity(String name, int id) {
        this.name = name;
        this.id = id;
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
