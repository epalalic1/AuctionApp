package com.developer.auctionapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

/**
 * <p>Image</p>
 *
 * Class that holds data about one Image and with annotation @Table we are creating that entity with all listed
 * relationships with other entities in our database
 */

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private  Long id;

    @Column(name = "name")
    private final String name;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private final Product product;

    public Image(
            final Long id,
            final String name,
            final Product product) {
        this.id = id;
        this.name = name;
        this.product = product;
    }

    public Image(
            final String name,
            final Product product) {
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
