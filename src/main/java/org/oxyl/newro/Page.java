package org.oxyl.newro;

import java.util.ArrayList;
import java.util.List;


public class Page<T> {
    private int nbRow = 50;

    private int pageNumber = 1;
    private List<T> content = new ArrayList<>();


    public Page() {
    }

    public int getNbRow() {
        return nbRow;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
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

    public void getListContent(List<T> content) {
        this.content = content;
    }

    public void addContent(T content) {
        this.content.add(content);
    }

    public void emptyContent() {
        this.content.clear();
    }

    public void display() {
        for (T line : content) {
            System.out.println(line.toString());
        }
    }
}
