package com.developer.auctionapp.controller;

import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.service.ProductService;
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

    @GetMapping("/")
    public void initialize() { productService.initializeProductTable();}

    @GetMapping("/getAll")
    public List<Product> findAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getNewProducts")
    public List<Product> getNewProducts() {
        return productService.getNewProducts();
    }

    @GetMapping("/getLastChanceProducts")
    public List<Product> getLastChanceProducts() {
        return productService.getLastChanceProducts();
    }
}
