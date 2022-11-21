package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.response.ProductResponseDto;
import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.repository.ProductRepository;
import com.developer.auctionapp.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/auctionapp/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAll")
    public List<ProductResponseDto> findAllProducts() {
        return productService.getAllProducts();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getNewProducts")
    public List<ProductResponseDto> getNewProducts() {
        return productService.getNewProducts();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getLastChanceProducts")
    public List<ProductResponseDto> getLastChanceProducts() {
        return productService.getLastChanceProducts();
    }
}
