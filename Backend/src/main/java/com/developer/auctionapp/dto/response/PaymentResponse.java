package com.developer.auctionapp.dto.response;

/**
 * <p>PaymentResponse</p>
 *
 * Data Transfer Object that we send when the payment is done
 */

public class PaymentResponse {
    private final String clientSecret;

    public PaymentResponse(final String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public PaymentResponse() {
        clientSecret = null;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}


