package com.developer.auctionapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notification_id")
    private long id;

    @Column(name = "message")
    private final String message;

    @Column(name = "date")
    private final ZonedDateTime date;

    @Column(name = "status")
    private final boolean status;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private final User user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private final Product product;

    public Notification(
            final long id,
            final String message,
            final ZonedDateTime date,
            final boolean status,
            final User user,
            final Product product) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.status = status;
        this.user = user;
        this.product = product;
    }

    public Notification(
            final String message,
            final ZonedDateTime date,
            final boolean status,
            final User user,
            final Product product) {
        this.message = message;
        this.date = date;
        this.status = status;
        this.user = user;
        this.product = product;
    }

    public Notification() {
        this.id = 0l;
        this.message = "";
        this.date = ZonedDateTime.now();
        this.status = false;
        this.user = new User();
        this.product = new Product();
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public boolean isStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }
}
