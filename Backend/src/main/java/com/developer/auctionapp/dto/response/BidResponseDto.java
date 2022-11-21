package com.developer.auctionapp.dto.response;

import java.time.ZonedDateTime;

public class BidResponseDto {
    Long id;
    Long amount;
    ZonedDateTime dateOfBid;
    Long productId;

    public BidResponseDto(Long id, Long amount, ZonedDateTime dateOfBid, Long productId) {
        this.id = id;
        this.amount = amount;
        this.dateOfBid = dateOfBid;
        this.productId = productId;
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
}

