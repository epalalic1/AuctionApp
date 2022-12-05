package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.response.CategoryResponse;
import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAll();
}
