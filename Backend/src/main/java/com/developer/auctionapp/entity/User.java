package com.developer.auctionapp.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    @Column(name = "name")
    private final String name;

    @Column(name = "surname")
    private final String surname;

    @Column(name = "email")
    private final String email;

    @Column(name = "password")
    private final String password;

    @Column(name = "phone")
    private final String phone;

    @Column(name = "gender")
    private final String gender;

    @Column(name = "dateOfBirth")
    private final ZonedDateTime dateOfBirth;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private List<Role> roles;


    public User() {
        id = null;
        name = null;
        surname = null;
        email = null;
        password = null;
        phone = null;
        gender = null;
        dateOfBirth = null;
    }

    public User(Long id,
                String name,
                String surname,
                String email,
                String password,
                String phone,
                String gender,
                ZonedDateTime dateOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;

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
}
