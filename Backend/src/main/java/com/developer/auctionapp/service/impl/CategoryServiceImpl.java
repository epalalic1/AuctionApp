package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.response.CategoryResponse;
import com.developer.auctionapp.entity.Category;
import com.developer.auctionapp.entity.Subcategory;
import com.developer.auctionapp.repository.CategoryRepository;
import com.developer.auctionapp.repository.SubcategoryRepository;
import com.developer.auctionapp.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>Class that implements CategoryService interface and we use it to comunicate with the database</p>
 */

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    public CategoryServiceImpl(final CategoryRepository categoryRepository, final SubcategoryRepository subcategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    /**
     * The method used to get all categories from database and transform them into Data Transform Objects
     * @return list of Data Transform Objects which each of them represent one Category
     */

    @Override
    public ResponseEntity<List<CategoryResponse>> getAll() {
        List<CategoryResponse> list = new ArrayList<>();
        List<Category> listOfCategories = categoryRepository.findAll();
        if (listOfCategories.size() == 0) {
            return ResponseEntity.noContent().build();
        }
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
        return ResponseEntity.of(Optional.of(list));
    }
}
