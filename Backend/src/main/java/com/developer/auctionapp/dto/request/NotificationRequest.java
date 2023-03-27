package com.developer.auctionapp.dto.request;

import java.time.ZonedDateTime;
import java.util.Objects;

public class NotificationRequest {

    private final String message;

    private final long userId;

    private final long productId;

    private final boolean status;

    public NotificationRequest(String message, long userId, long productId, boolean status) {
        this.message = message;
        this.userId = userId;
        this.productId = productId;
        this.status = status;
    }

    public NotificationRequest() {
        this.message = "";
        this.userId = 0;
        this.productId = 0;
        this.status = false;
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

