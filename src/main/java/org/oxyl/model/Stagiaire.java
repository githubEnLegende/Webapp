package org.oxyl.model;

import java.time.LocalDate;
import java.util.Optional;

import org.oxyl.persistence.PromotionDAO;


public class Stagiaire {
	
	private int id;
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
                ", promotionId=" + promotion.toString() +
				", Arrival=" + arrival +
				", formationOver=" + formationOver +
                '}';
    }
	
	public static class StagiaireBuilder{
		private int id;
	    private String firstName;
	    private String lastName;
	    private LocalDate arrival;
	    private LocalDate formationOver;
	    private Promotion promotion;
	    
	    public StagiaireBuilder (int id, String firstName, String lastName, LocalDate arrival, int promotion) {
	    	this.id = id;
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	    	this.arrival = arrival;
			Optional<Promotion> promotionOptional = PromotionDAO.getPromotion(promotion);
			if (promotionOptional.isPresent()) {
				this.promotion = promotionOptional.get();
			}else {
				this.promotion = null;
			}

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
	    
	    public Stagiaire build() {
	    	return new Stagiaire(this);
	    }

		
	}
}


