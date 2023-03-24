package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.request.AddItemRequest;
import com.developer.auctionapp.dto.response.BiddersForProduct;
import com.developer.auctionapp.dto.response.ProductResponse;
import com.developer.auctionapp.dto.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * An interface that contains all the methods that a ProductServiceImpl service should have
 */

public interface ProductService {

    ResponseEntity<List<ProductResponse>> getAllProducts();

    ResponseEntity<List<ProductResponse>>  getNewProducts();

    ResponseEntity<List<ProductResponse>>  getLastChanceProducts();

    Response addProduct(final AddItemRequest addItemRequest);

    ResponseEntity<List<BiddersForProduct>>  findBiddersForProduct(final Long id);

    ProductResponse  getProductFromId(final long id);
}
