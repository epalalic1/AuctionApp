package com.developer.auctionapp.dto.request;

/**
 * <p>ClassPaymentRequest</p>
 *
 * Data Transfer Object that get when user wants to make payment
 */

public class PaymentRequest {


    private final String currency;
    private final String description;
    private final int amount;
    private final String stripeEmail;
    private final String stripeToken;

    private long productId;

    public PaymentRequest() {
<<<<<<< HEAD
        currency = "";
        description = "";
        amount = 0;
        stripeEmail = "";
        stripeToken = "";
=======
        currency = null;
        description = null;
        amount = 0;
        stripeEmail = null;
        stripeToken = null;
>>>>>>> 3c6c8490 (Allow the payment on the Stripe)
    }

    public PaymentRequest(
            final String currency,
            final String description,
            final int amount,
            final String stripeEmail,
            final String stripeToken,
            final long productId
    ) {
        this.currency = currency;
        this.description = description;
        this.amount = amount;
        this.stripeEmail = stripeEmail;
        this.stripeToken = stripeToken;
        this.productId = productId;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }


    public String getStripeEmail() {
        return stripeEmail;
    }


    public String getStripeToken() {
        return stripeToken;
    }


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
