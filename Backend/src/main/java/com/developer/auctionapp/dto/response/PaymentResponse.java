package com.developer.auctionapp.dto.response;

public class PaymentResponse {
    private final String clientSecret;

    public PaymentResponse(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public PaymentResponse() {
        clientSecret = null;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
