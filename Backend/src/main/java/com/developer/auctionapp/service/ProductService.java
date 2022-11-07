package com.developer.auctionapp.service;
import com.developer.auctionapp.entity.Product;
import java.util.List;

public interface ProductService {
    void initializeProductTable();
    List<Product> getAllProducts();

    int getNumberOfRows();

    List<Product> getNewProducts();

    List<Product> getLastChanceProducts();

}
