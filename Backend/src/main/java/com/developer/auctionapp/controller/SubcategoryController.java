package com.developer.auctionapp.controller;

import com.developer.auctionapp.entity.Subcategory;
import com.developer.auctionapp.service.SubcategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p>Subcategory controller</p>
 *
 * The rest controller with REST API calls to manipulate with Subcategory objects on a route "/auctionapp/subcateogry"
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp/subcategory")
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/subcategory/getAll"</p>
     * @return all subcategories from the database
     */

    @GetMapping("/getAll")
    public List<Subcategory> findAllSubcategories() {
        return subcategoryService.getAllCategories();
    }
}
