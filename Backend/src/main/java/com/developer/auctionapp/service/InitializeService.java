package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.response.Response;

/**
 * An interface that contains all the methods that a InitializeServiceImpl service should have
 */

public interface InitializeService {

    Boolean initializeCategoryTable();


    void initializeSubcategoryTable();


    void initializeProductTable();


    void initializeBidTable();


    void initializeUserTable();


     void initializeImageTable();

     Response checkIfAnErrorOccurred();
}
