package com.developer.auctionapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    @Column(name = "name")
    private final String name;

    @ManyToOne
    @JsonIgnore
    private final Product product;
    
    public Image (Long id, String name, Product product) {
        this.id = id;
        this.name = name;
        this.product = product;
    }
    
    public Image() {
        id = null;
        name = null;
        product = null;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Product getProduct() {
        return product;
    }
}
