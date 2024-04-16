package model;

import java.sql.Timestamp;

public class Stagiaire {
	
	private int id;
    private String firstName;
    private String lastName;
    private Timestamp arrival;
    private Timestamp formationOver;
    private int promotionId;
    
    public Stagiaire() {}
    
	public Stagiaire(String firstName, String lastName, Timestamp arrival, Timestamp formationOver,
			int promotion) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.arrival = arrival;
		this.formationOver = formationOver;
		this.promotionId = promotion;
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
	public Timestamp getArrival() {
		return arrival;
	}
	public void setArrival(Timestamp arrival) {
		this.arrival = arrival;
	}
	public Timestamp getFormationOver() {
		return formationOver;
	}
	public void setFormationOver(Timestamp formationOver) {
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
                ", arrival=" + arrival.toString() +
                ", formationOver=" + formationOver.toString() +
                ", promotionId=" + promotionId +
                '}';
    }
}
