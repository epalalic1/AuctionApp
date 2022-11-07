package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.entity.Category;
import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.repository.CategoryRepository;
import com.developer.auctionapp.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public int getNumberofRows() {
        return categoryRepository.getNumberOfRows();
    }

    @Override
    public void initializeCategoryTable() {
        if (getNumberofRows() != 0) {
           return;
        }
        List<Category> listOfCategories = new ArrayList<>();
        Category category1 = new Category(1L,"Fashion");
        Category category2 = new Category(2L,"Accessories");
        Category category3 = new Category(3L,"Jewlery");
        Category category4 = new Category(4L,"Shoes");
        Category category5 = new Category(5L,"Sportwear");
        Category category6 = new Category(6L,"Home");
        Category category7 = new Category(7L,"Electronics");
        Category category8 = new Category(8L,"Mobile");
        Category category9 = new Category(9L,"Computer");
        listOfCategories.add(category1);
        listOfCategories.add(category2);
        listOfCategories.add(category3);
        listOfCategories.add(category4);
        listOfCategories.add(category5);
        listOfCategories.add(category6);
        listOfCategories.add(category7);
        listOfCategories.add(category8);
        listOfCategories.add(category9);
        categoryRepository.saveAll(listOfCategories);
    }
}
