package com.ca.fire.test.domain;

import com.ca.fire.annotations.UserParamValidator;

import java.math.BigDecimal;

public class Book {

    public String color;

    private long id;

    @UserParamValidator(value = "消费")
    private String bookName;

    private BigDecimal price;

    private Float weight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }


    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Book(String bookName, BigDecimal price) {
        this.bookName = bookName;
        this.price = price;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
