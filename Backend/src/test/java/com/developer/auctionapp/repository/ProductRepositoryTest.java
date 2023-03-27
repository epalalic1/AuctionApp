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

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductRepositoryTest {

    @Autowired ProductRepository productRepository;

    @Autowired private  SubcategoryRepository subcategoryRepository;

    @Autowired private CategoryRepository categoryRepository;

    @Autowired private UserRepository userRepository;

    Category category = new Category("newCategory");

    Subcategory subcategory = new Subcategory("newSubcategory", category);

    User user = new User("user","user","user","user","user","user", ZonedDateTime.now());

    Product product1 = new Product("product1",ZonedDateTime.now().minusMonths(2),ZonedDateTime.now(),1l,"",false,1l,subcategory, user);

    Product product2 = new Product("product2",ZonedDateTime.now().minusMonths(1),ZonedDateTime.now(),1l,"",false,1l,subcategory, user);

    Product product3 = new Product("product3",ZonedDateTime.now(),ZonedDateTime.now().minusDays(7),1l,"",false,1l,subcategory, user);

    Product product4 = new Product("product4",ZonedDateTime.now(),ZonedDateTime.now().minusDays(7),1l,"",false,1l,subcategory, user);

    Product product5 = new Product("product5",ZonedDateTime.now(),ZonedDateTime.now().minusDays(7),1l,"",false,1l,subcategory, user);

    Product product6 = new Product("product6",ZonedDateTime.now(),ZonedDateTime.now().plusDays(8),1l,"",false,1l,subcategory, user);


    @BeforeAll
    public void initialize() {
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

    @Test
    void findByDateOfArrivingAfter() {
        assertEquals(productRepository.findByDateOfArrivingAfter(ZonedDateTime.now().minusMonths(3)).size(),2);
        //poslati datum ZonedDateTime.now().minusMonths(3)
        //treba vratiti prva dva proizvoda
    }

    @Test
    void findByEndDateBefore() {
        assertEquals(productRepository.findByEndDateBefore(ZonedDateTime.now()).size(), 3);
        //treba vratiti 3 4 5 proizvod
        //poslati danasnji dan
    }

    @Test
    void findByEndDateAfter() {
        assertEquals(productRepository.findByEndDateBefore(ZonedDateTime.now()).size(), 1);
        //treba vratiti sesti proizvod
        //poslati danasnji datum
    }
}