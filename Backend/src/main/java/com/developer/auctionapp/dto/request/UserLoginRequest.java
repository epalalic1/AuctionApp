package com.developer.auctionapp.dto.request;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class UserLoginRequest {
    @NotNull
    @NotEmpty
    private final String email;

    @NotNull
    @NotEmpty
    private final String password;

    public UserLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
