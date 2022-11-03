package com.developer.auctionapp.service.impl;


import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.repository.ProductRepository;
import com.developer.auctionapp.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProducts() {
       return productRepository.findAll();
    }

    @Override
    public int getNumberOfRows() {
        return productRepository.getNumberOfRows();
    }

    @Override
    public void initializeProductTable() {
        productRepository.initializeProductTable();
    }

    @Override
    public List<Product> getNewProducts() {
        return productRepository.getNewProducts();
    }

    @Override
    public List<Product> getLastChanceProducts() {
        return productRepository.getLastChanceProducts();
    }

}
