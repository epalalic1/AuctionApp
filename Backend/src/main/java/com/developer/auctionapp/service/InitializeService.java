package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.response.Response;

/**
 * An interface that contains all the methods that a InitializeServiceImpl service should have
 */

public interface InitializeService {

    Boolean initializeCategoryTable();

    void initializeSubcategoryTable();

    void initializeRoleTable();


     Response checkIfAnErrorOccurred();
}
