package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.response.Response;

/**
 * An interface that contains all the methods that a InitializeServiceImpl service should have
 */

public interface InitializeService {

    Boolean initializeCategoryTable();

    int getNumberofRowsCategoryTable();

    void initializeSubcategoryTable();

    int getNumberofRowsSubcategoryTable();

    void initializeProductTable();

    int getNumberOfRowsProductTable();

    void initializeBidTable();

    int getNumberofRowsBidTable();

    void initializeUserTable();

    int getNumberofRowsUserTable();

    int getNumberofRowsRoleTable();

    int getNumberofRowsImageTable();

     void initializeImageTable();

     Response checkIfAnErrorOccurred();
}
