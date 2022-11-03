package com.developer.auctionapp.service;



import com.developer.auctionapp.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    int getNumberOfRows();

    void initializeProductTable();

    List<Product> getNewProducts();

    List<Product> getLastChanceProducts();

}
