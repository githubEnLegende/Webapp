package org.oxyl.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class Page<T> {
    private long nbRow = 50;
    private String order = "id";
    private long pageNumber = 1;
    private long totalPages = 0;
    private long count = 0;
    private List<T> content = new ArrayList<>();

    public Page() {
    }

    public Page(
            long nbRow,
            String order,
            long pageNumber,
            long totalPages,
            List<T> content
    ) {
        this.nbRow = nbRow;
        this.order = order;
        this.pageNumber = pageNumber;
        this.totalPages = totalPages;
        this.content = content;
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

    public <U> Page<U> map(Function<T, U> function) {
        return new Page<>(
                nbRow,
                order,
                pageNumber,
                totalPages,
                content.stream().map(
                        function
                ).toList()
        );
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }
}
