package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.response.ProductResponse;
import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts();

    List<ProductResponse> getNewProducts();

    List<ProductResponse> getLastChanceProducts();
}
