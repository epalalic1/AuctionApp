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
    private final Long id;

    @Column(name = "name")
    private final String name;

    @Column(name = "dateOfArriving")
    private final ZonedDateTime dateOfArriving;

    @Column(name = "endDate")
    private final ZonedDateTime endDate;

    @Column(name = "startPrice")
    private final Long startPrice;

    @Column(name = "details")
    private final String details;

    @Column(name = "status")
    private final Boolean status;

    @Column(name = "price")
    private final Long price;

    @ManyToOne
    @JsonIgnore
    private final Subcategory subcategory;

    @ManyToOne
    @JsonIgnore
    private final User user;

    public Product() {
        id = null;
        name = null;
        dateOfArriving = null;
        endDate = null;
        startPrice = null;
        details = null;
        status = null;
        price = null;
        subcategory = null;
        user = null;
    }

    public Product(
            Long id,
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

    public String getName() {
        return name;
    }

    public ZonedDateTime getDateOfArriving() {
        return dateOfArriving;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public String getDetails() {
        return details;
    }

    public Boolean getStatus() {
        return status;
    }

    public Long getPrice() {
        return price;
    }

    public Subcategory getCategory() {
        return subcategory;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public User getUser() {
        return user;
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