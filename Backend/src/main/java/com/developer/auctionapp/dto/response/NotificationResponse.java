package com.developer.auctionapp.dto.response;

import java.time.ZonedDateTime;

public class NotificationResponse {

    private final String message;

    private final ZonedDateTime date;

    private final boolean status;

    private  final long  userId;

    private final long  productId;


    public NotificationResponse(
            final String message,
            final ZonedDateTime date,
            final boolean status,
            final long userId,
            final long productId) {
        this.message = message;
        this.date = date;
        this.status = status;
        this.userId = userId;
        this.productId = productId;
    }
}
