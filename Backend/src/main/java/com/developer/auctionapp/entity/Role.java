package com.developer.auctionapp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Role</p>
 *
 * Class that holds data about one Role and with annotation @Table we are creating that entity in our database
 */

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private final String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private final List<User> users;

    public Role(final Long id, final String name) {
        this.id = id;
        this.name = name;
        users = new ArrayList<>();
    }

    public Role(final String name) {
        this.name = name;
        users = new ArrayList<>();
    }

    public Role() {
        id = 0l;
        name = "";
        users = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }
}
