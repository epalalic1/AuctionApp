package com.developer.auctionapp.dto.response;

import java.time.ZonedDateTime;

public class NotificationResponse {

    private final String message;

    private  final long  userId;

    private final long  productId;

    private final boolean status;

    public NotificationResponse(
            final String message,
            final long userId,
            final long productId,
            final boolean status
    ) {
        this.message = message;
        this.userId = userId;
        this.productId = productId;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public long getUserId() {
        return userId;
    }

    public long getProductId() {
        return productId;
    }

    public boolean isStatus() {
        return status;
    }
}
