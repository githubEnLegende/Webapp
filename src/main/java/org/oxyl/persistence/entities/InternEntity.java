package org.oxyl.persistence.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="intern")
public class InternEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "arrival")
    private LocalDate arrival;

    @Column(name = "formation_over")
    private LocalDate formationOver;

    @Column(name = "promotion_id")
    private int promotionId;

    public InternEntity() {

    }

    public InternEntity(int id, String firstName, String lastName, LocalDate arrival, LocalDate formationOver, int promotionId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.arrival = arrival;
        this.formationOver = formationOver;
        this.promotionId = promotionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public LocalDate getFormationOver() {
        return formationOver;
    }

    public void setFormationOver(LocalDate formationOver) {
        this.formationOver = formationOver;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }
}
