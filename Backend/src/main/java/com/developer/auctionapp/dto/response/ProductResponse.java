package com.developer.auctionapp.dto.response;

import java.time.ZonedDateTime;
import java.util.List;

public class ProductResponse {

    private final Long id;

    private final String name;

    private final ZonedDateTime dateOfArriving;

    private final ZonedDateTime endDate;

    private final Long startPrice;

    private final String details;

    private final Boolean status;

    private final Long price;

    private final Long subcategoryId;

    private final Long userId;

    private final List<String> imageName;

    private final Long categoryId;

    public ProductResponse(
            Long id,
            String name,
            ZonedDateTime dateOfArriving,
            ZonedDateTime endDate,
            Long startPrice,
            String details,
            Boolean status,
            Long price,
            Long subcategoryId,
            Long userId,
            List<String>  imageName,
            Long categoryId) {
        this.id = id;
        this.name = name;
        this.dateOfArriving = dateOfArriving;
        this.endDate = endDate;
        this.startPrice = startPrice;
        this.details = details;
        this.status = status;
        this.price = price;
        this.subcategoryId = subcategoryId;
        this.userId = userId;
        this.imageName = imageName;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ZonedDateTime getDateOfArriving() {
        return dateOfArriving;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public String getDetails() {
        return details;
    }

    public Boolean getStatus() {
        return status;
    }

    public Long getPrice() {
        return price;
    }

    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public List<String> getImageName() {
        return imageName;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
