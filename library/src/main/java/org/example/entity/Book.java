package org.example.entity;


import lombok.Data;

@Data
public class Book {
    private Integer id;
    private String name;
    private String bookContent;
    private String orderInformation;
    private String bookNumOnlibrary;
    private String bookKind;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bookContent='" + bookContent + '\'' +
                ", orderInformation='" + orderInformation + '\'' +
                ", bookNumOnlibrary='" + bookNumOnlibrary + '\'' +
                ", bookKind='" + bookKind + '\'' +
                ", bookWrittentime='" + bookWrittentime + '\'' +
                '}';
    }

    private String bookWrittentime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookContent() {
        return bookContent;
    }

    public void setBookContent(String bookContent) {
        this.bookContent = bookContent;
    }

    public String getOrderInformation() {
        return orderInformation;
    }

    public void setOrderInformation(String orderInformation) {
        this.orderInformation = orderInformation;
    }

    public String getBookNumOnlibrary() {
        return bookNumOnlibrary;
    }

    public void setBookNumOnlibrary(String bookNumOnlibrary) {
        this.bookNumOnlibrary = bookNumOnlibrary;
    }

    public String getBookKind() {
        return bookKind;
    }

    public void setBookKind(String bookKind) {
        this.bookKind = bookKind;
    }

    public String getBookWrittentime() {
        return bookWrittentime;
    }

    public void setBookWrittentime(String bookWrittentime) {
        this.bookWrittentime = bookWrittentime;
    }
}

