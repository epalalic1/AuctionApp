package com.developer.auctionapp.entity;

import javax.persistence.*;

/**
 * <p>Subcategory</p>
 *
 * Class that holds data about one Subcategory and with annotation @Table we are creating that entity with all listed
 * relationships with other entities in our database
 */


@Entity
@Table(name = "subcategory")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subcategory_id",unique = true)
    private Long id;

    @Column(name = "name")
    private final String name;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private final Category category;

    public Subcategory(
            final Long id,
            final String name,
            final Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Subcategory(final String name, final Category category) {
        this.name = name;
        this.category = category;
    }

    public Subcategory() {
        id = 0l;
        name = "";
        category = new Category();
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
