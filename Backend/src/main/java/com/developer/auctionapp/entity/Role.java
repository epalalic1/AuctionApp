package com.developer.auctionapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    @Column(name = "name")
    private final String name;

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private final List<User> users;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
        users = null;
    }
    public Role() {
        id = null;
        name = null;
        users = null;
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
