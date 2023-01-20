package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.request.AddItemRequest;
import com.developer.auctionapp.dto.response.ProductResponse;
import com.developer.auctionapp.dto.response.Response;

import java.util.List;

/**
 * An interface that contains all the methods that a ProductServiceImpl service should have
 */

public interface ProductService {

    List<ProductResponse> getAllProducts();

    List<ProductResponse> getNewProducts();

    List<ProductResponse> getLastChanceProducts();

    Response addProduct(AddItemRequest addItemRequest);
}
