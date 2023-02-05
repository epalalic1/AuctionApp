package com.developer.auctionapp.dto.response;

import java.util.Date;

/**
 * <p>BiddersForProduct</p>
 *
 * Data Transfer Object that we send to the frontend as response when we want to know all bidder
 * for product
 */

public class BiddersForProduct {

    private final String name;

    private final Date dateOfBid;

    private final long amount;


    public BiddersForProduct(
            final String name,
            final Date dateOfBid,
            final  long amount
    ) {
        this.name = name;
        this.dateOfBid = dateOfBid;
        this.amount = amount;
    }

    public BiddersForProduct() {
        name = "";
        dateOfBid = new Date();
        amount = 0;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBid() {
        return dateOfBid;
    }

    public long getAmount() {
        return amount;
    }
}
