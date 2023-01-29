package com.developer.auctionapp.entity;

import javax.persistence.*;

/**
 * <p>Category</p>
 *
 * Class that holds data about one Category and with annotation @Table we are creating that entity in out database
 */

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id",unique = true)
    private Long id;

    @Column(name = "name")
    private final String name;

    public Category(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Category (final String name) {
        this.name = name;
    }

    public Category() {
        id = 0l;
        name = "";
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
