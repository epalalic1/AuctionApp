package com.developer.auctionapp.dto.response;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>ProductResponse</p>
 *
 * Data Transfer Object that we send to the frontend as response and we transform Product object into it
 */

public class ProductResponse {

    private  Long id;

    private  String name;

    private ZonedDateTime dateOfArriving;

    private final  ZonedDateTime endDate;

    private  Long startPrice;

    private String details;

    private  Boolean status;

    private  Long price;

    private  Long subcategoryId;

    private  Long userId;

    private List<String> imageName;

    private  Long categoryId;

    public ProductResponse(
            final Long id,
            final String name,
            final ZonedDateTime dateOfArriving,
            final ZonedDateTime endDate,
            final Long startPrice,
            final String details,
            final Boolean status,
            final Long price,
            final Long subcategoryId,
            final Long userId,
            final List<String>  imageName,
            final Long categoryId) {
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
    public ProductResponse(
            final String name,
            final ZonedDateTime dateOfArriving,
            final ZonedDateTime endDate,
            final Long startPrice,
            final String details,
            final Boolean status,
            final Long price,
            final Long subcategoryId,
            final Long userId,
            final List<String>  imageName,
            final Long categoryId) {
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

    public ProductResponse() {
        id = 0l;
        name = "";
        dateOfArriving = ZonedDateTime.now();
        endDate = ZonedDateTime.now();
        startPrice = 0l;
        details = "";
        status = false;
        price = 0l;
        subcategoryId = 0l;
        userId = 0l;
        imageName = new ArrayList<>();
        categoryId = 0l;
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

    public void setId(Long id) {
        this.id = id;
    }
}
