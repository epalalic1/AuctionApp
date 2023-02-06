package com.developer.auctionapp.dto.response;

/**
 * <p>SubcategoryResponse</p>
 *
 * Data Transfer Object that we send to the frontend as response and we transform Subcategory object into it
 */

public class SubcategoryResponse {

    private final long id;

    private final String name;

    private final long category;


    public SubcategoryResponse(
            final long id,
            final String name,
            final long category
    ) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public SubcategoryResponse() {
        id = 0;
        name = null;
        category = 0;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getCategory() {
        return category;
    }
}
