package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Role;
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
class RoleRepositoryTest {

    @Autowired private RoleRepository roleRepository;

    Role role = new Role("newRole");

    @BeforeAll
    public void initialize() {
        roleRepository.save(role);
    }

    @AfterAll
    public void deleteData() {
        roleRepository.delete(role);
    }

    @Test
    void findByName() {
        assertNotNull(roleRepository.findByName("newRole"));
        assertEquals("newRole", roleRepository.findByName("newRole").getName());
        assertEquals(0, roleRepository.findByName("newRole").getUsers().size());
    }
}