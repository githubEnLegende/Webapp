package org.oxyl.model;

public class Chapitre {
	private int id;
	private String name;
	private String parent;
	
	
	public Chapitre(ChapitreBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.parent = builder.parent;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	
	public static class ChapitreBuilder{
		private int id;
		private String name;
		private String parent;
		
		public ChapitreBuilder(int id, String name, String parent) {
			this.id = id;
			this.name = name;
			this.parent = parent;
		}
		
		public ChapitreBuilder id(int id) {
			this.id = id;
			return this;
		}
		
		public ChapitreBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public ChapitreBuilder parent(String parent) {
			this.parent = parent;
			return this;
		}
		
		public Chapitre build() {
			return new Chapitre(this);
		}
		
		
	}
	
	
}
