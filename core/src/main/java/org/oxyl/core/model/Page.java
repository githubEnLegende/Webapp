package org.oxyl.core.model;

import java.util.ArrayList;
import java.util.List;


public class Page<T> {
    private long nbRow = 50;
    private String order = "id";
    private long pageNumber = 1;
    private long totalPages = 0;
    private List<T> content = new ArrayList<>();

    public Page() {
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getNbRow() {
        return nbRow;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setNbRow(long nbRow) {
        this.nbRow = nbRow;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
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
