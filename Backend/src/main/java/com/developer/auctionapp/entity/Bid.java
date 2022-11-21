package com.developer.auctionapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "bid")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id")
    private Long id;

    @Column(name = "amount")
    private Long amount;

    @Column(name ="dateOfBid")
    private ZonedDateTime dateOfBid;

    @ManyToOne
    private Product product;

    @ManyToOne
    @JsonIgnore
    private User user;

    public Bid(){}

    public Bid(Long id, Long amount, ZonedDateTime dateOfBid, Product product, User user) {
        this.id = id;
        this.amount = amount;
        this.dateOfBid = dateOfBid;
        this.product = product;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public ZonedDateTime getDateOfBid() {
        return dateOfBid;
    }

    public void setDateOfBid(ZonedDateTime dateOfBid) {
        this.dateOfBid = dateOfBid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
