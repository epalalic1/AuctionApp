package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.response.SubcategoryResponse;
import com.developer.auctionapp.entity.Subcategory;
import com.developer.auctionapp.repository.SubcategoryRepository;
import com.developer.auctionapp.service.SubcategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Class that implements SubcategoryService interface and we use it to comunicate with the database</p>
 */

@Service
@Transactional
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;

    public SubcategoryServiceImpl(final SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    /**
     * The method used to get all subcategories from database and transform them into Data Transform Objects
     *
     * @return list of Data Transform Objects which each of them represent one Subcategory
     */

    @Override
    public List<SubcategoryResponse> getAllSubcategories() {
        List<SubcategoryResponse> list = new ArrayList<>();
        final List<Subcategory> allSubcategories = subcategoryRepository.findAll();
        for (Subcategory item : allSubcategories) {
            SubcategoryResponse subcategoryResponse = new SubcategoryResponse(
                    item.getId(),
                    item.getName(),
                    item.getCategory().getId()
            );
            list.add(subcategoryResponse);
        }
        return list;
    }
}
