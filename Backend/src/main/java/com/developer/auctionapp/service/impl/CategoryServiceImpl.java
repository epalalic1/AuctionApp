package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.entity.Category;
import com.developer.auctionapp.repository.CategoryRepository;
import com.developer.auctionapp.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
