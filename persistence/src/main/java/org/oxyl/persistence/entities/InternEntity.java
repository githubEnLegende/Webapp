package org.oxyl.persistence.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "intern")
public class InternEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "arrival", nullable = false)
    private LocalDate arrival;

    @Column(name = "formation_over")
    private LocalDate formationOver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id", nullable = false)
    private PromotionEntity promotion;

    public InternEntity() {

    }

    public InternEntity(String firstName, String lastName, LocalDate arrival, LocalDate formationOver, PromotionEntity promotion) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.arrival = arrival;
        this.formationOver = formationOver;
        this.promotion = promotion;
    }

    public InternEntity(long id, String firstName, String lastName, LocalDate arrival, LocalDate formationOver, PromotionEntity promotion) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.arrival = arrival;
        this.formationOver = formationOver;
        this.promotion = promotion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public PromotionEntity getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionEntity promotion) {
        this.promotion = promotion;
    }
}
