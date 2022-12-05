package com.developer.auctionapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "subcategory")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id")
    private final Long id;

    @Column(name = "name")
    private final String name;

    @ManyToOne
    private final Category category;

    public Subcategory() {
        id = null;
        name = null;
        category = null;
    }

    public Subcategory(
            Long id,
            String name,
            Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
