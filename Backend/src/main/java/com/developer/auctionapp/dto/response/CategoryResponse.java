package com.developer.auctionapp.dto.response;

import java.util.List;

public class CategoryResponse {

    private Long Id;

    private String Name;

    private List<String> subcategory;

    private boolean isChecked;

    private CategoryResponse() {}

    public CategoryResponse(
            Long id,
            String name,
            List<String> subcategory,
            boolean isChecked) {
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
