package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.response.CategoryResponse;
import com.developer.auctionapp.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p>Category controller</p>
 *
 * The rest controller with REST API calls to manipulate with Category objects on a route "/auctionapp/category"
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/category/getAll"</p>
     * @return all categories from the database
     */

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryResponse>> getAll(){
        return categoryService.getAll();}
}
