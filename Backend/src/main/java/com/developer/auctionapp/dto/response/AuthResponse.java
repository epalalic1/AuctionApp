package com.developer.auctionapp.dto.response;

/**
 * <p>AuthResponse</p>
 *
 * Data Transfer Object that we send to the frontend as response when user is successfully logged-in
 */

public class AuthResponse {

    private final String accessToken;

    private final String tokenType = "Bearer ";

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
