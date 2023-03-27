package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.response.Response;
import org.springframework.http.ResponseEntity;

/**
 * An interface that contains all the methods that a InitializeServiceImpl service should have
 */

public interface InitializeService {

    ResponseEntity<Object> initializeCategoryTable();

    ResponseEntity<Object> initializeSubcategoryTable();

    ResponseEntity<Object> initializeRoleTable();

    ResponseEntity<Object> initializeDatabase();
}
