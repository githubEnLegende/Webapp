package newro;

import java.util.ArrayList;
import java.util.List;


public class Page<T> {
	private int nbRow = 50;
	private List<T> content = new ArrayList<>();
	
	
	public Page() {
	}
	
	public int getNbRow() {
		return nbRow;
	}
	public void setNbRow(int nbRow) {
		this.nbRow = nbRow;
	}
	public List<T> getStagiaires() {
		return content;
	}
	public void setStagiaires(List<T> content) {
		this.content = content;
	}
	
	public void addContent(T content) {
		this.content.add(content);
	}
	
	public void emptyContent() {
		this.content.clear();
	}
	
	public void display() {
		for(T lign : content) {
			System.out.println(lign.toString());
		}
	}

	
	
	
}
