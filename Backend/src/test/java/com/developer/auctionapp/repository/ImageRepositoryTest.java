package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class that contains tests for testing the methods of the ImageRepository interface
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    final private Category category = new Category("newCategory");

    final private Subcategory subcategory = new Subcategory("newSubcategory", category);

    final private User firstUser = new User("user1", "user1", "user1", "user1", "user1", "user1", ZonedDateTime.now().minusYears(20));

    final private Product product = new Product("Necklace", ZonedDateTime.now(), ZonedDateTime.now().plusMonths(1), 1l, "a", false, 1l, subcategory, firstUser);

    final private Image firstImage = new Image("firstImage", product);

    final private Image secondImage = new Image("secondImage", product);

    final private Image thirdImage = new Image("thirdImage", product);

    /**
     * A method that initializes the database
     */

    @BeforeAll
    public void initialize() {
        categoryRepository.save(category);
        subcategoryRepository.save(subcategory);
        userRepository.save(firstUser);
        productRepository.save(product);
        imageRepository.save(firstImage);
        imageRepository.save(secondImage);
        imageRepository.save(thirdImage);
    }

    /**
     * A method that deletes data from the database
     */

    @AfterAll
    public void deleteData() {
        imageRepository.delete(firstImage);
        imageRepository.delete(secondImage);
        imageRepository.delete(thirdImage);
        productRepository.delete(product);
        userRepository.delete(firstUser);
        subcategoryRepository.delete(subcategory);
        categoryRepository.delete(category);
    }

    /**
     * A method that tests the repository method that returns image by product
     */

    @Test
    void findByProduct() {
        assertNotNull(imageRepository.findByProduct(product));
        assertEquals(3, imageRepository.findByProduct(product).size());
        assertEquals("firstImage", imageRepository.findByProduct(product).get(0).getName());
        assertEquals("thirdImage", imageRepository.findByProduct(product).get(2).getName());
    }
}