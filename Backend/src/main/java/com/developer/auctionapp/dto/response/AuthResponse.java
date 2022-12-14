package com.developer.auctionapp.dto.response;

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
