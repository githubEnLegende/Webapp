package org.oxyl.model;

import java.time.LocalDate;


public class Stagiaire {

    private long id;
    private String firstName;
    private String lastName;
    private LocalDate arrival;
    private LocalDate formationOver;
    private Promotion promotion;


    private Stagiaire(StagiaireBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.arrival = builder.arrival;
        this.formationOver = builder.formationOver;
        this.promotion = builder.promotion;
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

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public String toString() {
        return "Stagiaire{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", promotion=" + promotion.getName() +
                ", Arrival=" + arrival +
                ", formationOver=" + formationOver +
                '}';
    }

    public static class StagiaireBuilder {
        private long id;
        private String firstName;
        private String lastName;
        private LocalDate arrival;
        private LocalDate formationOver;
        private Promotion promotion;

        public StagiaireBuilder(long id, String firstName, String lastName, LocalDate arrival) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.arrival = arrival;
        }

        public StagiaireBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public StagiaireBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public StagiaireBuilder arrival(LocalDate arrival) {
            this.arrival = arrival;
            return this;
        }

        public StagiaireBuilder formationOver(LocalDate formationOver) {
            this.formationOver = formationOver;
            return this;
        }

        public StagiaireBuilder promotion(Promotion promotion) {
            this.promotion = promotion;
            return this;
        }

//		public StagiaireBuilder promotion(long promotion){
//			Optional<Promotion> promotionOptional = promotionDAO.getPromotion(promotion);
//			if (promotionOptional.isPresent()) {
//				this.promotion = promotionOptional.get();
//			}else {
//				this.promotion = null;
//			}
//			return this;
//		}

        public Stagiaire build() {
            return new Stagiaire(this);
        }


    }
}


