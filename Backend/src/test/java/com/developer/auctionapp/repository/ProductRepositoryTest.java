package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Category;
import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.entity.Subcategory;
import com.developer.auctionapp.entity.User;
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
 * A class that contains tests for testing the methods of the ProductRepository interface
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BidRepository bidRepository;

    final Category category = new Category("newCategory");

    final Subcategory subcategory = new Subcategory("newSubcategory", category);

    final User user = new User("user", "user", "user", "user", "user", "user", ZonedDateTime.now());

    final Product product1 = new Product("product1", ZonedDateTime.now().minusMonths(2), ZonedDateTime.now().plusDays(1), 1l, "", false, 1l, subcategory, user);

    final Product product2 = new Product("product2", ZonedDateTime.now().minusMonths(1), ZonedDateTime.now().plusDays(2), 1l, "", false, 1l, subcategory, user);

    final Product product3 = new Product("product3", ZonedDateTime.now().minusMonths(4), ZonedDateTime.now().minusDays(7), 1l, "", false, 1l, subcategory, user);

    final Product product4 = new Product("product4", ZonedDateTime.now().minusMonths(5), ZonedDateTime.now().minusDays(7), 1l, "", false, 1l, subcategory, user);

    final Product product5 = new Product("product5", ZonedDateTime.now().minusMonths(6), ZonedDateTime.now().minusDays(7), 1l, "", false, 1l, subcategory, user);

    final Product product6 = new Product("product6", ZonedDateTime.now().minusMonths(4), ZonedDateTime.now().plusDays(8), 1l, "", false, 1l, subcategory, user);

    /**
     * A method that initializes the database
     */

    @BeforeAll
    public void initialize() {
        bidRepository.deleteAll();
        productRepository.deleteAll();
        categoryRepository.save(category);
        subcategoryRepository.save(subcategory);
        userRepository.save(user);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);
    }

    /**
     * A method that deletes data from the database
     */

    @AfterAll
    public void deleteData() {
        productRepository.delete(product1);
        productRepository.delete(product2);
        productRepository.delete(product3);
        productRepository.delete(product4);
        productRepository.delete(product5);
        productRepository.delete(product6);
        subcategoryRepository.delete(subcategory);
        categoryRepository.delete(category);
        userRepository.delete(user);
    }

    /**
     * A method that tests the repository method that returns products whose date of arriving is after
     * specific date
     */

    @Test
    void findByDateOfArrivingAfter() {
        assertEquals(2, productRepository.findByDateOfArrivingAfter(ZonedDateTime.now().minusMonths(3)).size());
        assertEquals("product1", productRepository.findByDateOfArrivingAfter(ZonedDateTime.now().minusMonths(3)).get(0).getName());
        assertEquals("product2", productRepository.findByDateOfArrivingAfter(ZonedDateTime.now().minusMonths(3)).get(1).getName());
    }

    /**
     * A method that tests the repository method that returns products whose end date is before
     * specific date
     */

    @Test
    void findByEndDateBefore() {
        assertEquals(productRepository.findByEndDateBefore(ZonedDateTime.now()).size(), 3);
        assertEquals("product3", productRepository.findByEndDateBefore(ZonedDateTime.now()).get(0).getName());
        assertEquals("product4", productRepository.findByEndDateBefore(ZonedDateTime.now()).get(1).getName());
        assertEquals("product5", productRepository.findByEndDateBefore(ZonedDateTime.now()).get(2).getName());
    }

    /**
     * A method that tests the repository method that returns products whose end date is after
     * specific date
     */

    @Test
    void findByEndDateAfter() {
        assertEquals(1, productRepository.findByEndDateAfter(ZonedDateTime.now().plusDays(5)).size());
        assertEquals("product6", productRepository.findByEndDateAfter(ZonedDateTime.now().plusDays(5)).get(0).getName());
    }
}