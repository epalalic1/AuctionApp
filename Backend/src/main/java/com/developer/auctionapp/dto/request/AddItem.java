package com.developer.auctionapp.dto.request;

import java.util.Date;

/**
 * Data Transfer Object that we receive from a request made in frontend when the user try to add one
 * product
 */

public class AddItem {
    private final String name;

    private final String category;

    private final String subcategory;

    private final String description;

    private final String imageName;

    private final int startPrice;

    private final Date startDate;

    private final Date endDate;

    public AddItem(
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

    public AddItem() {
        name = null;
        category = null;
        subcategory = null;
        description = null;
        imageName = null;
        startPrice = 0;
        startDate = null;
        endDate = null;
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
