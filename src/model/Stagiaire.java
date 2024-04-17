package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Stagiaire {
	
	private int id;
    private String firstName;
    private String lastName;
    private LocalDate arrival;
    private LocalDate formationOver;
    private int promotionId;
    
    public Stagiaire() {}
    
	public Stagiaire(int id, String firstName, String lastName, LocalDate arrival, LocalDate formationOver,
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
			this.firstName = rs.getString("first_name");
			this.lastName = rs.getString("last_name");
			this.arrival = rs.getTimestamp("arrival")
					== null ? null : rs.getTimestamp("arrival").toLocalDateTime().toLocalDate();
			this.formationOver = rs.getTimestamp("formation_over") 
					== null ? null : rs.getTimestamp("formation_over").toLocalDateTime().toLocalDate();
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
}
