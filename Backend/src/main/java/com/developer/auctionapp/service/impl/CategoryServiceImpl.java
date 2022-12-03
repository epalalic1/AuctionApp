package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.response.CategoryResponse;
import com.developer.auctionapp.entity.Category;
import com.developer.auctionapp.entity.Subcategory;
import com.developer.auctionapp.repository.CategoryRepository;
import com.developer.auctionapp.repository.SubcategoryRepository;
import com.developer.auctionapp.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<CategoryResponse> list = new ArrayList<>();
        List<Category> listOfCategories = categoryRepository.findAll();
        for (Category item : listOfCategories) {
            List<Subcategory> subcategories = subcategoryRepository.findByCategory(item);
            List<String> names = subcategories.stream().map(Subcategory::getName).collect(Collectors.toList());
            CategoryResponse res = new CategoryResponse(
                    item.getId(),
                    item.getName(),
                    names,
                    false
            );
            list.add(res);
        }
        return list;
    }
}
