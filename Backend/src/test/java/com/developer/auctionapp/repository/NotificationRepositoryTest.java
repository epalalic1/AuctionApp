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
class NotificationRepositoryTest {

    @Autowired private  SubcategoryRepository subcategoryRepository;

    @Autowired private UserRepository userRepository;

    @Autowired private  ProductRepository productRepository;

    @Autowired private CategoryRepository categoryRepository;

    @Autowired private NotificationRepository notificationRepository;

    private Category category = new Category("newCategory");

    private Subcategory subcategory = new Subcategory("newSubcategory", category);

    private User firstUser = new User("user1","user1","user1","user1","user1","user1", ZonedDateTime.now().minusYears(20));

    private Product firstProduct = new Product("firstProduct", ZonedDateTime.now(),ZonedDateTime.now().plusMonths(1),1l,"a",false,1l,subcategory,firstUser);

    private Product secondProduct = new Product("secondProduct", ZonedDateTime.now(),ZonedDateTime.now().plusMonths(2),5l,"a",false,1l,subcategory,firstUser);

    private Notification firstNotification = new Notification("message1",ZonedDateTime.now(),false,firstUser,firstProduct);

    private Notification secondNotification = new Notification("message2", ZonedDateTime.now().minusDays(1),false, firstUser,firstProduct);

    private Notification thirdNotification = new Notification("message3",ZonedDateTime.now().minusMonths(1),false,firstUser,secondProduct);

    @BeforeAll
    public void initialize() {
        categoryRepository.save(category);
        subcategoryRepository.save(subcategory);
        userRepository.save(firstUser);
        productRepository.save(firstProduct);
        productRepository.save(secondProduct);
        notificationRepository.save(firstNotification);
        notificationRepository.save(secondNotification);
        notificationRepository.save(thirdNotification);
    }

    @AfterAll
    public void deleteData() {
        notificationRepository.delete(firstNotification);
        notificationRepository.delete(secondNotification);
        notificationRepository.delete(thirdNotification);
        productRepository.delete(firstProduct);
        productRepository.delete(secondProduct);
        subcategoryRepository.delete(subcategory);
        categoryRepository.delete(category);
        userRepository.delete(firstUser);
    }

    @Test
    void findByUser() {
        assertNotNull(notificationRepository.findByUser(firstUser));
        assertEquals(notificationRepository.findByUser(firstUser).size(),3);
        assertEquals(notificationRepository.findByUser(firstUser).get(0).getMessage(),"message1");
        assertEquals(notificationRepository.findByUser(firstUser).get(1).getMessage(),"message2");
        assertEquals(notificationRepository.findByUser(firstUser).get(0).getProduct().getName(), "firstProduct");
        assertEquals(notificationRepository.findByUser(firstUser).get(2).getProduct().getName(), "secondProduct");
    }
}