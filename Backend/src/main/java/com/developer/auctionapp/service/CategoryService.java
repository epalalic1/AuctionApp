package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.response.CategoryResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * An interface that contains all the methods that a CategoryServiceImpl service should have
 */

public interface CategoryService {

    ResponseEntity<List<CategoryResponse>> getAll();
}
