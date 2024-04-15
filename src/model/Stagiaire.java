package model;

import java.time.LocalDate;

public class Stagiaire {
	
	private long id;
    private String firstName;
    private String lastName;
    private LocalDate arrival;
    private LocalDate formationOver;
    private Promotion promotion;
    
    public Stagiaire() {}
    
	public Stagiaire(long id, String firstName, String lastName, LocalDate arrival, LocalDate formationOver,
			Promotion promotion) {
		super();
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
                ", arrival=" + arrival.toString() +
                ", formationOver=" + formationOver.toString() +
                ", promotionId=" + promotion.toString() +
                '}';
    }
}
