package com.developer.auctionapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * <p>Bid</p>
 *
 * Class that holds data about one Bid and with annotation @Table we are creating that entity with all listed
 * relationships with other entities in our database
 */

@Entity
@Table(name = "bid")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bid_id")
    private Long id;

    @Column(name = "amount")
    private final Long amount;

    @Column(name = "dateOfBid")
    private final ZonedDateTime dateOfBid;

    @ManyToOne
    private final Product product;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private final User user;

    public Bid(
            final Long id,
            final Long amount,
            final ZonedDateTime dateOfBid,
            final Product product,
            final User user) {
        this.id = id;
        this.amount = amount;
        this.dateOfBid = dateOfBid;
        this.product = product;
        this.user = user;
    }

    public Bid(
            final Long amount,
            final ZonedDateTime dateOfBid,
            final Product product,
            final User user) {
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
