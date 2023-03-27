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


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ImageRepositoryTest {

    @Autowired private ImageRepository imageRepository;

    @Autowired private  SubcategoryRepository subcategoryRepository;

    @Autowired private UserRepository userRepository;

    @Autowired private  ProductRepository productRepository;

    @Autowired private CategoryRepository categoryRepository;

    private Category category = new Category("newCategory");

    private Subcategory subcategory = new Subcategory("newSubcategory", category);

    private User firstUser = new User("user1","user1","user1","user1","user1","user1", ZonedDateTime.now().minusYears(20));

    private Product product = new Product("Necklace", ZonedDateTime.now(),ZonedDateTime.now().plusMonths(1),1l,"a",false,1l,subcategory,firstUser);

    private Image firstImage = new Image("firstImage", product);

    private Image secondImage = new Image("secondImage", product);

    private Image thirdImage = new Image("thirdImage", product);

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

    @Test
    void findByProduct() {
        assertNotNull(imageRepository.findByProduct(product));
        assertEquals(imageRepository.findByProduct(product).size(),3);
        assertEquals(imageRepository.findByProduct(product).get(0).getName(), "firstImage");
        assertEquals(imageRepository.findByProduct(product).get(2).getName(),"thirdImage");
    }
}