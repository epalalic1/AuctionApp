package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.response.Response;
import com.developer.auctionapp.entity.*;
import com.developer.auctionapp.repository.*;
import com.developer.auctionapp.service.InitializeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Class that implements InitializeService  interface and we use it to comunicate with the database
 * to seed the database with the initial data</p>
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class InitializeServiceImpl implements InitializeService {

    private final CategoryRepository categoryRepository;

    private final SubcategoryRepository subcategoryRepository;

    private final RoleRepository roleRepository;


    public InitializeServiceImpl(
            final CategoryRepository categoryRepository,
            final SubcategoryRepository subcategoryRepository,
            final RoleRepository roleRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.roleRepository = roleRepository;
    }

    private String initializeCategory = "";

    private String initializeSubcategory = "";

    private String initializeRole = "";

    private String initializeAddress = "";


    /**
     * A method that inserts data into the Category table after checking that no data already exists in the table
     */

    @Override
    public ResponseEntity<Object> initializeCategoryTable() {
        if (categoryRepository.findAll().size() != 0) {
            return ResponseEntity.ok().build();
        }
        List<Category> listOfCategories = new ArrayList<>();
        Category category1 = new Category("Men");
        Category category2 = new Category("Woman");
        Category category3 = new Category("Kids");
        Category category4 = new Category("Home");
        Category category5 = new Category("Art");
        Category category6 = new Category("Accessories");
        Category category7 = new Category("Copmputers");
        Category category8 = new Category("Mobile");
        Category category9 = new Category("Sportwear");
        Category category10 = new Category("Electronics");
        listOfCategories.add(category1);
        listOfCategories.add(category2);
        listOfCategories.add(category3);
        listOfCategories.add(category4);
        listOfCategories.add(category5);
        listOfCategories.add(category6);
        listOfCategories.add(category7);
        listOfCategories.add(category8);
        listOfCategories.add(category9);
        listOfCategories.add(category10);
        try {
            categoryRepository.saveAll(listOfCategories);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * A method that inserts data into the Subcategory table after checking that no data already exists in the table
     */

    @Override
    public ResponseEntity<Object> initializeSubcategoryTable() {
        if (subcategoryRepository.findAll().size() != 0) {
            return ResponseEntity.ok().build();
        }
        List<Subcategory> listOfSubcategories = new ArrayList<>();
        Subcategory subcategory1 = new Subcategory("Bags", categoryRepository.findByName("Woman"));
        Subcategory subcategory2 = new Subcategory("Accessories", categoryRepository.findByName("Woman"));
        Subcategory subcategory3 = new Subcategory("Jewlery", categoryRepository.findByName("Woman"));
        Subcategory subcategory4 = new Subcategory("Shoes", categoryRepository.findByName("Woman"));
        Subcategory subcategory5 = new Subcategory("Sportwear", categoryRepository.findByName("Men"));
        Subcategory subcategory6 = new Subcategory("Chairs", categoryRepository.findByName("Home"));
        Subcategory subcategory7 = new Subcategory("Tables", categoryRepository.findByName("Home"));
        Subcategory subcategory8 = new Subcategory("Toys", categoryRepository.findByName("Kids"));
        Subcategory subcategory9 = new Subcategory("Books", categoryRepository.findByName("Kids"));
        listOfSubcategories.add(subcategory1);
        listOfSubcategories.add(subcategory2);
        listOfSubcategories.add(subcategory3);
        listOfSubcategories.add(subcategory4);
        listOfSubcategories.add(subcategory5);
        listOfSubcategories.add(subcategory6);
        listOfSubcategories.add(subcategory7);
        listOfSubcategories.add(subcategory8);
        listOfSubcategories.add(subcategory9);
        try {
            subcategoryRepository.saveAll(listOfSubcategories);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * A method that inserts data into the User and Role tables after checking that no data already exists in the table
     */

    @Override
    public ResponseEntity<Object> initializeRoleTable() {
        if (roleRepository.findAll().size() != 0) {
            return ResponseEntity.ok().build();
        }
        try {
            List<Role> listOfRoles = new ArrayList<>();
            Role role1 = new Role(1L, "Admin");
            Role role2 = new Role(2L, "Logged in");
            listOfRoles.add(role1);
            listOfRoles.add(role2);
            this.roleRepository.saveAll(listOfRoles);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public ResponseEntity<Object> initializeDatabase() {
        int statusCodeCategory = initializeCategoryTable().getStatusCodeValue();
        int statusCodeSubcategory = initializeSubcategoryTable().getStatusCodeValue();
        int statusCodeRole = initializeRoleTable().getStatusCodeValue();
        if (statusCodeCategory == 200
                && statusCodeSubcategory== 200
                && statusCodeRole == 200) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
