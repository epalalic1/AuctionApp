package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.response.Response;

public interface InitializeService {

    void initializeCategoryTable();

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
