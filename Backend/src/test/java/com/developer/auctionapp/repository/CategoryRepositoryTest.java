package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Category;
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
class CategoryRepositoryTest {

    @Autowired CategoryRepository categoryRepository;

    private Category category = new Category("newCategory");

    @BeforeAll
    public void initialize() {
        categoryRepository.save(category);
    }

    @AfterAll
    public void deleteData() {
        categoryRepository.delete(category);
    }


    @Test
    void findByName() {
        assertNotNull(categoryRepository.findByName("newCategory"));
        assertEquals("newCategory", categoryRepository.findByName("newCategory").getName());
    }

}