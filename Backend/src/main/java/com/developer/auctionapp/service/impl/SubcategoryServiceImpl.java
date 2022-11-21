package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.entity.Category;
import com.developer.auctionapp.entity.Subcategory;
import com.developer.auctionapp.repository.SubcategoryRepository;
import com.developer.auctionapp.service.SubcategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;

    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public List<Subcategory> getAllCategories() {
        return subcategoryRepository.findAll();
    }


}
