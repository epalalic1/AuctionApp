package com.developer.auctionapp.service;
import com.developer.auctionapp.dto.response.ProductResponseDto;
import com.developer.auctionapp.entity.Product;
import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getAllProducts();

    List<ProductResponseDto> getNewProducts();

    List<ProductResponseDto> getLastChanceProducts();

}
