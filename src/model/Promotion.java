package model;

import java.time.LocalDate;

public class Promotion {
	private long id;
	private LocalDate promotion;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getPromotion() {
		return promotion;
	}
	public void setPromotion(LocalDate promotion) {
		this.promotion = promotion;
	}
	
	public String toString() {
		return "PromotionID : " + id + " " + promotion.toString();
	}
	
}
