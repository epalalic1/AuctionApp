package com.developer.auctionapp.controller;

import com.developer.auctionapp.entity.Subcategory;
import com.developer.auctionapp.service.SubcategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/auctionapp/subcategory")
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAll")
    public List<Subcategory> findAllSubcategories() {
        return subcategoryService.getAllCategories();
    }
}
