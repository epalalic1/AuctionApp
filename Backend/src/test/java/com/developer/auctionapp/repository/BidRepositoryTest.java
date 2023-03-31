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
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BidRepositoryTest {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    final private Category category = new Category("Woman");
    final private Subcategory subcategory = new Subcategory("Accessories", category);
    final private User firstUser = new User("user1", "user1", "user1", "user1", "user1", "user1", ZonedDateTime.now().minusYears(20));
    final private User secondUser = new User("user1", "user1", "user1", "user1", "user1", "user1", ZonedDateTime.now().minusYears(20));
    final private Product product = new Product("Necklace", ZonedDateTime.now(), ZonedDateTime.now().plusMonths(1), 1l, "a", false, 1l, subcategory, firstUser);
    final private Bid bid = new Bid(120l, ZonedDateTime.now().plusMonths(5), product, secondUser);

    /**
     * A method that initializes the database
     */

    @BeforeAll
    public void initialize() {
        categoryRepository.save(category);
        subcategoryRepository.save(subcategory);
        userRepository.save(firstUser);
        userRepository.save(secondUser);
        productRepository.save(product);
        bidRepository.save(bid);
    }

    /**
     * A method that deletes data from the database
     */

    @AfterAll
    public void deleteData() {
        bidRepository.delete(bid);
        productRepository.delete(product);
        subcategoryRepository.delete(subcategory);
        categoryRepository.delete(category);
        userRepository.delete(firstUser);
        userRepository.delete(secondUser);
    }

    /**
     * A method that tests the repository method that returns bid by product
     */

    @Test
    void findByProduct() {
        assertNotNull(bidRepository.findByProduct(product));
        assertEquals(product.getName(), bidRepository.findByProduct(product).get(0).getProduct().getName());
        assertEquals(secondUser.getName(), bidRepository.findByProduct(product).get(0).getUser().getName());
    }
}