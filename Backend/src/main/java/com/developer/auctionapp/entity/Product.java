package com.developer.auctionapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * <p>Product</p>
 *
 * Class that holds data about one Product and with annotation @Table we are creating that entity with all listed
 * relationships with other entities in our database
 */

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

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
    private  Boolean status;

    @Column(name = "price")
    private final Long price;

    @ManyToOne
    @JsonIgnore
    private final Subcategory subcategory;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private final User user;

    public Product(
            final Long id,
            final String name,
            final ZonedDateTime dateOfArriving,
            final ZonedDateTime endDate,
            final Long startPrice,
            final String details,
            final Boolean status,
            final Long price,
            final Subcategory subcategory,
            final User user) {
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

    public Product(
            final String name,
            final ZonedDateTime dateOfArriving,
            final ZonedDateTime endDate,
            final Long startPrice,
            final String details,
            final Boolean status,
            final Long price,
            final Subcategory subcategory,
            final User user) {
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

    public Product() {
        id = 0l;
        name = "";
        dateOfArriving = ZonedDateTime.now();
        endDate = ZonedDateTime.now();
        startPrice = 0l;
        details = "";
        status = false;
        price = 0l;
        subcategory = new Subcategory();
        user = new User();
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

    public void setStatus(Boolean status) {
        this.status = status;
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