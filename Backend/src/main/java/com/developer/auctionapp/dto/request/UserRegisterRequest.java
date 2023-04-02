package com.developer.auctionapp.dto.request;

import com.sun.istack.NotNull;
import javax.validation.constraints.NotEmpty;

/**
 * <p>Class UserRegisterRequest</p>
 * Data Transfer Object that we receive from a request made in frontend when we try to register user
 */

public class UserRegisterRequest {

    @NotNull
    @NotEmpty
    private final String firstName;

    @NotNull
    @NotEmpty
    private final String lastName;

    @NotNull
    @NotEmpty
    private final String email;

    @NotNull
    @NotEmpty
    private final String password;

    public UserRegisterRequest(
            final String firstName,
            final String lastName,
            final String email,
            final String password
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserRegisterRequest() {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
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

    public String getPassword() {
        return password;
    }

}
