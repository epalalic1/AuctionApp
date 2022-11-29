package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.response.CategoryResponse;
import com.developer.auctionapp.entity.Category;
import com.developer.auctionapp.service.CategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
<<<<<<< HEAD
    public List<Category> getAll() {
        return categoryService.getAll();
    }
=======
    public List<CategoryResponse> getAll(){
        return categoryService.getAll();}
>>>>>>> cc96bda6 (Change the way we are getting all categories)
}
