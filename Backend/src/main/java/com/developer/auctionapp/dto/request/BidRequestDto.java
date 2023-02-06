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
    Long amount;
    ZonedDateTime dateOfBid;
    Long productId;
    Long userId;

    public BidRequestDto(){}

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public ZonedDateTime getDateOfBid() {
        return dateOfBid;
    }

    public void setDateOfBid(ZonedDateTime dateOfBid) {
        this.dateOfBid = dateOfBid;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
