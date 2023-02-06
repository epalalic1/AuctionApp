package com.developer.auctionapp.service;

import com.developer.auctionapp.entity.Subcategory;
import java.util.List;

/**
 * An interface that contains all the methods that a SubcategoryServiceImpl service should have
 */

public interface SubcategoryService {

    List<Subcategory> getAllCategories();
}
