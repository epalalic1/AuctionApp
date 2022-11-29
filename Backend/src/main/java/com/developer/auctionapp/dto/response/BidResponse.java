package com.developer.auctionapp.dto.response;

import java.time.ZonedDateTime;

public class BidResponse {
    private final Long id;

    private final Long amount;

    private final ZonedDateTime dateOfBid;

    private final Long productId;

    public BidResponse(
            Long id,
            Long amount,
            ZonedDateTime dateOfBid,
            Long productId) {
        this.id = id;
        this.amount = amount;
        this.dateOfBid = dateOfBid;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }

    public ZonedDateTime getDateOfBid() {
        return dateOfBid;
    }

    public Long getProductId() {
        return productId;
    }
}
