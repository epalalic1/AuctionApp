package com.developer.auctionapp.dto.response;

import java.util.List;

/**
 * <p>CategoryResponse</p>
 *
 * Data Transfer Object that we send to the frontend as response and we transform Category object into it
 */

public class CategoryResponse {

    private Long Id;

    private String Name;

    private List<String> subcategory;

    private boolean isChecked;

    private CategoryResponse() {
    }

    public CategoryResponse(
            final Long id,
            final String name,
            final List<String> subcategory,
            final boolean isChecked) {
        Id = id;
        Name = name;
        this.subcategory = subcategory;
        this.isChecked = isChecked;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<String> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<String> subcategory) {
        this.subcategory = subcategory;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
