package com.developer.auctionapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name ="name")
    private String name;

    @Column(name ="dateOfArriving")
    private ZonedDateTime dateOfArriving;

    @Column(name ="endDate")
    private ZonedDateTime endDate;

    @Column(name ="startPrice")
    private Long startPrice;

    @Column(name ="details")
    private String details;

    @Column(name ="status")
    private Boolean status;

    @Column(name ="price")
    private Long price;

    @ManyToOne
    @JsonIgnore
    private Subcategory subcategory;

    @ManyToOne
    @JsonIgnore
    private User user;
    public Product() {
    }

    public Product(Long id,
                   String name,
                   ZonedDateTime dateOfArriving,
                   ZonedDateTime endDate,
                   Long startPrice,
                   String details,
                   Boolean status,
                   Long price,
                   Subcategory subcategory,
                   User user) {
        this.id = id;
        this.name = name;
        this.dateOfArriving = dateOfArriving;
        this.endDate = endDate;
        this.startPrice = startPrice;
        this.details = details;
        this.status = status;
        this.price = price;
        this.subcategory = subcategory;
        this.user = user;
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

    public ZonedDateTime getDateOfArriving() {
        return dateOfArriving;
    }

    public void setDateOfArriving(ZonedDateTime dateOfArriving) {
        this.dateOfArriving = dateOfArriving;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
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

    public Subcategory getCategory() {
        return subcategory;
    }

    public void setCategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Product c = (Product) o;
        return Double.compare(id, c.id) == 0;
    }
}

