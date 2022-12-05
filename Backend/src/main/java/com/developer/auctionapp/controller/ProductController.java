package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.response.ProductResponse;
import com.developer.auctionapp.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public List<ProductResponse> findAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getNewProducts")
    public List<ProductResponse> getNewProducts() {
        return productService.getNewProducts();
    }

    @GetMapping("/getLastChanceProducts")
    public List<ProductResponse> getLastChanceProducts() {
        return productService.getLastChanceProducts();
    }
}
