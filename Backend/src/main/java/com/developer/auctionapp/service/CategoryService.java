package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.response.CategoryResponse;
import com.developer.auctionapp.entity.Category;
import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAll();
}
