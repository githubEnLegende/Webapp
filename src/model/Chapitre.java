package model;

public class Chapitre {
	private int id;
	private String name;
	private String parent;
	
	
	public Chapitre(int id, String name, String parent) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
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
	
	
}
