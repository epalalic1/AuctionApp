package com.developer.auctionapp.dto.response;

import java.time.ZonedDateTime;

/**
 * <p>BidResponse</p>
 *
 * Data Transfer Object that we send to the frontend as response and we transform Bid object into it
 */

public class BidResponse {
    private final Long id;

    private final Long amount;

    private final ZonedDateTime dateOfBid;

    private final Long productId;

    public BidResponse(
            final Long id,
            final Long amount,
            final ZonedDateTime dateOfBid,
            final Long productId) {
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
