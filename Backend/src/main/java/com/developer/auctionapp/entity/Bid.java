package com.developer.auctionapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "bid")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id")
    private final Long id;

    @Column(name = "amount")
    private final Long amount;

    @Column(name ="dateOfBid")
    private final ZonedDateTime dateOfBid;

    @ManyToOne
    private final Product product;

    @ManyToOne
    @JsonIgnore
    private final User user;

    public Bid(Long id, Long amount, ZonedDateTime dateOfBid, Product product, User user){
        this.id = id;
        this.amount = amount;
        this.dateOfBid = dateOfBid;
        this.product = product;
        this.user = user;
    }
    public Bid() {
        id = null;
        amount = null;
        dateOfBid = null;
        product = null;
        user = null;
    }

    public Long getId() {
        return id;
    }


    public Long getAmount() {
        return amount;
    }

    public ZonedDateTime getDateOfBid() {
        return dateOfBid;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }
}
