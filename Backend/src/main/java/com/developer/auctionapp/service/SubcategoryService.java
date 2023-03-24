package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.response.SubcategoryResponse;
import com.developer.auctionapp.entity.Subcategory;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * An interface that contains all the methods that a SubcategoryServiceImpl service should have
 */

public interface SubcategoryService {

    ResponseEntity<List<SubcategoryResponse>> getAllSubcategories();
}
