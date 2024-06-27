package model;

import java.time.LocalDate;

public class Stagiaire {
	
	private int id;
    private String firstName;
    private String lastName;
    private LocalDate arrival;
    private LocalDate formationOver;
    private int promotionId;
    
    
	private Stagiaire(StagiaireBuilder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.arrival = builder.arrival;
		this.formationOver = builder.formationOver;
		if (builder.promotionId > 47) {
			builder.promotionId = 47;
			System.out.println("ID trop élevé, mis à la dernière promotion");
		}
		else if (builder.promotionId < 1) {
			builder.promotionId = 1;
			System.out.println("ID trop bas, mis à la première promotion");

		}
		this.promotionId = builder.promotionId;
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
	public int getPromotion() {
		return promotionId;
	}
	public void setPromotion(int promotion) {
		this.promotionId = promotion;
	}
	
	public String toString() {
        return "Stagiaire{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", promotionId=" + promotionId +
                '}';
    }
	
	public static class StagiaireBuilder{
		private int id;
	    private String firstName;
	    private String lastName;
	    private LocalDate arrival;
	    private LocalDate formationOver;
	    private int promotionId;
	    
	    public StagiaireBuilder (int id, String firstName, String lastName, LocalDate arrival, int promotionId) {
	    	this.id = id;
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	    	this.arrival = arrival;
	    	this.promotionId = promotionId;
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
	    
	    public StagiaireBuilder promotionId(int promotionId) {
	    	this.promotionId = promotionId;
	    	return this;
	    }
	    
	    public Stagiaire build() {
	    	return new Stagiaire(this);
	    }

		
	}
}


