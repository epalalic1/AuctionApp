package com.developer.auctionapp.service;

import com.developer.auctionapp.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    void initializeCategoryTable();

    int getNumberofRows();
}
