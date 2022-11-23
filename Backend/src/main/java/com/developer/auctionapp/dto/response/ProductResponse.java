package com.developer.auctionapp.dto.response;

import java.time.ZonedDateTime;

public class ProductResponse {

    private Long id;

    private String name;

    private ZonedDateTime dateOfArriving;

    private ZonedDateTime endDate;

    private Long startPrice;

    private String details;

    private Boolean status;

    private Long price;

    private Long subcategoryId;

    private Long userId;

    private String imageName;

    public ProductResponse(){}

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
                           String imageName) {
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
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getDateOfArriving() {
        return dateOfArriving;
    }

    public void setDateOfArriving(ZonedDateTime dateOfArriving) {
        this.dateOfArriving = dateOfArriving;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
