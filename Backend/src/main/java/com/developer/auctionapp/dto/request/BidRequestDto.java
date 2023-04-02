package com.developer.auctionapp.dto.request;

import java.time.ZonedDateTime;

/**
 * <p>Class BidRequestDto</p>
 *
 * Data Transfer Object that we receive from a request made in frontend and can be easily  trasnformed
 * into Bid object
 */

public class BidRequestDto {

    Long id;
    final Long amount;
    final ZonedDateTime dateOfBid;
    final Long productId;
    final Long userId;

    public BidRequestDto(){
        this.id = 0l;
        this.amount = 0l;
        this.dateOfBid = ZonedDateTime.now();
        this.productId = 0l;
        this.userId = 0l;
    }

    public BidRequestDto(
        final Long id,
        final Long amount,
        final ZonedDateTime dateOfBid,
        final Long productId,
        final Long userId)
        {
        this.id = id;
        this.amount = amount;
        this.dateOfBid = dateOfBid;
        this.productId = productId;
        this.userId = userId;
    }

    public BidRequestDto(
            final Long amount,
            final ZonedDateTime dateOfBid,
            final Long productId,
            final Long userId)
    {
        this.amount = amount;
        this.dateOfBid = dateOfBid;
        this.productId = productId;
        this.userId = userId;
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


    public Long getUserId() {
        return userId;
    }

}
