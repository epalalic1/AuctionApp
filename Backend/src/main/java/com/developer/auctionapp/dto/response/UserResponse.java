package com.developer.auctionapp.dto.response;

import java.time.ZonedDateTime;

public class UserResponse {

    private final Long id;

    private final String name;

    private final String surname;

    private final String email;

    private final String password;

    private final String phone;

    private final String gender;

    private final ZonedDateTime dateOfBirth;

    private final Long  roleId;


    public UserResponse(
                Long id,
                String name,
                String surname,
                String email,
                String password,
                String phone,
                String gender,
                ZonedDateTime dateOfBirth,
                Long roleId){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.roleId = roleId;
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

    public Long getRoleId() {
        return roleId;
    }
}
