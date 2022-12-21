package com.developer.auctionapp.dto.request;

public class UpdateUser {

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String phone;

    public UpdateUser(
            final String firstName,
            final String lastName,
            final String email,
            final String phone
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public UpdateUser() {
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.phone = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
