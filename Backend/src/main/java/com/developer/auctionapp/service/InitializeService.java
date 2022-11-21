package com.developer.auctionapp.service;

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

    void initializeRoleTable();

    int getNumberofRowsRoleTable();

    void initializeUserRoleTable();

    int getNumberofRowsUserRoleTable();

    int getNumberofRowsImageTable();

    void initializeImageTable();

}
