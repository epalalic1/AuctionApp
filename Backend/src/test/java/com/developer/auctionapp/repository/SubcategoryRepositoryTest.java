package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Category;
import com.developer.auctionapp.entity.Subcategory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SubcategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    final Category category = new Category("newCategory");

    final Subcategory subcategory1 = new Subcategory("subcategory1", category);

    final Subcategory subcategory2 = new Subcategory("subcategory2", category);

    /**
     * A method that initializes the database
     */

    @BeforeAll
    public void initialize() {
        categoryRepository.save(category);
        subcategoryRepository.save(subcategory1);
        subcategoryRepository.save(subcategory2);
    }

    /**
     * A method that deletes data from the database
     */

    @AfterAll
    public void deleteData() {
        subcategoryRepository.delete(subcategory1);
        subcategoryRepository.delete(subcategory2);
        categoryRepository.delete(category);
    }

    /**
     * A method that tests the repository method that returns subcategory by category
     */

    @Test
    void findByCategory() {
        assertNotNull(subcategoryRepository.findByCategory(category));
        assertEquals(2, subcategoryRepository.findByCategory(category).size());
        assertEquals("subcategory1", subcategoryRepository.findByCategory(category).get(0).getName());
    }

    /**
     * A method that tests the repository method that returns subcategory by name
     */

    @Test
    void findByName() {
        assertNotNull(subcategoryRepository.findByName("subcategory2"));
        assertEquals("subcategory2", subcategoryRepository.findByName("subcategory2").getName());
    }
}