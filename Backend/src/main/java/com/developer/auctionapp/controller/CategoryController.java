package com.developer.auctionapp.controller;

import com.developer.auctionapp.entity.Category;
import com.developer.auctionapp.service.CategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auctionapp/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAll")
    public List<Category> getAll(){
        return categoryService.getAll();}
}
