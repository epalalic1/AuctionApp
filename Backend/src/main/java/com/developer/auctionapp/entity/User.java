package com.developer.auctionapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * <p>User</p>
 *
 * Class that holds data about one User and with annotation @Table we are creating that entity with all listed
 * relationships with other entities in our database
 */

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private  String surname;

    @Column(name = "email")
    private  String email;

    @Column(name = "password")
    private final String password;

    @Column(name = "phone")
    private  String phone;

    @Column(name = "gender")
    private final String gender;

    @Column(name = "dateOfBirth")
    private final ZonedDateTime dateOfBirth;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinTable(name = "users_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private List<Role> roles;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private final Address address;


    public User() {
        id =0l;
        name = "";
        surname = "";
        email = "";
        password = "";
        phone = "";
        gender = "";
        dateOfBirth = ZonedDateTime.now();
        address = new Address();
    }

    public User(
            final Long id,
            final String name,
            final String surname,
            final String email,
            final String password,
            final String phone,
            final String gender,
            final ZonedDateTime dateOfBirth,
            final Address address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public User(
            final String name,
            final String surname,
            final String email,
            final String password,
            final String phone,
            final String gender,
            final ZonedDateTime dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        address = null;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public ZonedDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }
}
