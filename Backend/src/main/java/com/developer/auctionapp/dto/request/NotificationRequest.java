package com.developer.auctionapp.dto.request;

import java.time.ZonedDateTime;

public class NotificationRequest {

    private final String text;

    private final ZonedDateTime date;

    private final long productId ;

    public NotificationRequest(String text, ZonedDateTime date, long productId) {
        this.text = text;
        this.date = date;
        this.productId = productId;
    }

    public NotificationRequest() {
        this.text = "";
        this.date = ZonedDateTime.now();
        this.productId = 0l;
    }

    public String getText() {
        return text;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public long getProductId() {
        return productId;
    }
}
