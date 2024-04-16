package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Stagiaire {
	
	private int id;
    private String firstName;
    private String lastName;
    private Timestamp arrival;
    private Timestamp formationOver;
    private int promotionId;
    
    public Stagiaire() {}
    
	public Stagiaire(int id, String firstName, String lastName, Timestamp arrival, Timestamp formationOver,
			int promotion) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.arrival = arrival;
		this.formationOver = formationOver;
		this.promotionId = promotion;
	}
	
	public Stagiaire(ResultSet rs) {
		try {
			this.id = rs.getInt("id");
			this.firstName = rs.getString("text");
			this.lastName = rs.getString("text");
			this.arrival = rs.getTimestamp("arrival");
			this.formationOver = rs.getTimestamp("formation_over");
			this.promotionId = rs.getInt("promotion_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
