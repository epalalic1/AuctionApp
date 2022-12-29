package com.developer.auctionapp.dto.response;

import java.time.ZonedDateTime;
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

    private  ZonedDateTime endDate;

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

    public ProductResponse() {
        id = null;
        name = null;
        dateOfArriving = null;
        endDate = null;
        startPrice = null;
        details = null;
        status = null;
        price = null;
        subcategoryId = null;
        userId = null;
        imageName = null;
        categoryId = null;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfArriving(ZonedDateTime dateOfArriving) {
        this.dateOfArriving = dateOfArriving;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public void setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setImageName(List<String> imageName) {
        this.imageName = imageName;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
