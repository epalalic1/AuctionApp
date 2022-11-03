package com.developer.auctionapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;
    @Column(name ="name")
    private String name;
    @Column(name ="date_of_arriving")
    private Date date_of_arriving;
    @Column(name ="end_date")
    private Date end_date;
    @Column(name ="start_price")
    private Long start_price;
    @Column(name ="details")
    private String details;
    @Column(name ="status")
    private Boolean status;
    @Column(name ="price")
    private Long price;
    @ManyToOne
    @JsonIgnore
    private Category category;

    public Product() {
    }

    public Product(String name, Date date_of_arriving, Date end_date, Long start_price, String details, Boolean status, Long price, Category category) {
        this.name = name;
        this.date_of_arriving = date_of_arriving;
        this.end_date = end_date;
        this.start_price = start_price;
        this.details = details;
        this.status = status;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_of_arriving() {
        return date_of_arriving;
    }

    public void setDate_of_arriving(Date date_of_arriving) {
        this.date_of_arriving = date_of_arriving;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Long getStart_price() {
        return start_price;
    }

    public void setStart_price(Long start_price) {
        this.start_price = start_price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
