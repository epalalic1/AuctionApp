package com.developer.auctionapp.repository;

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
class UserRepositoryTest {

    @Autowired private UserRepository userRepository;

    User user = new User ("nameuser1","surnameuser1","email@gmail.com","password","123456","m", ZonedDateTime.now().minusYears(20));

    @BeforeAll
    public void initialize() {
        userRepository.save(user);
    }

    @AfterAll
    public void deleteData() {
        userRepository.delete(user);
    }

    @Test
    void findByEmail() {
        assertNotNull(userRepository.findByEmail("email@gmail.com"));
        assertEquals("nameuser1", userRepository.findByEmail("email@gmail.com").getName());
    }
}