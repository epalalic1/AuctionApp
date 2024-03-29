package com.developer.auctionapp.dto.request;

import com.stripe.model.Event;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Data Transfer Object that we receive from a request made in frontend when the user try to add one
 * product
 */

public class AddItemRequest {
    private final String name;

    private final String category;

    private final String subcategory;

    private final String description;

    private final String imageName;

    private final int startPrice;

    private final Date startDate;

    private final Date endDate;

    public AddItemRequest(
            final String name,
            final String category,
            final String subcategory,
            final String description,
            final String imageName,
            final int startPrice,
            final Date startDate,
            final Date endDate
    ) {
        this.name = name;
        this.category = category;
        this.subcategory = subcategory;
        this.description = description;
        this.imageName = imageName;
        this.startPrice = startPrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public AddItemRequest() {
        name = "";
        category = "";
        subcategory = "";
        description = "";
        imageName = "";
        startPrice = 0;
        startDate = new Date();
        endDate = new Date();;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getDescription() {
        return description;
    }

    public String getImageName() {
        return imageName;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
