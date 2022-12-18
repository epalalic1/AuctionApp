package com.developer.auctionapp.dto.request;

import com.sun.istack.NotNull;
import javax.validation.constraints.NotEmpty;

/**
 * <p>Class UserLoginRequest</p>
 * Data Transfer Object that we receive from a request made in frontend when we try to log in
 */

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
