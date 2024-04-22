package org.oxyl.model;

public class Promotion {
	private int id;
	private String promotion;
	
	
	
	public Promotion(PromotionBuilder builder) {
		this.id = builder.id;
		this.promotion = builder.promotion;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	
	public String toString() {		
		return "PromotionID : " + id + " " + promotion;
	}
	
	public static class PromotionBuilder{
		private int id;
		private String promotion;
		
		public PromotionBuilder(int id, String promotion){
			this.id = id;
			this.promotion = promotion;
		}
		
		public PromotionBuilder id(int id) {
			this.id = id;
			return this;
		}
		
		public PromotionBuilder promotion(String promotion) {
			this.promotion = promotion;
			return this;
		}
		
		public Promotion build() {
			return new Promotion(this);
		}
	}
	
}
