package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RoleServiceImplTest {

    @Mock private RoleServiceImpl roleServiceImpl;

    @Test
    void getAllRoles() {
        List<Role> list = new ArrayList<>();
        Role role1 = new Role("role1");
        Role role2 = new Role ("role2");
        Role role3 =  new Role("role3");
        list.add(role1);
        list.add(role2);
        list.add(role3);
        Mockito.when(roleServiceImpl.getAllRoles()).thenReturn(ResponseEntity.of(Optional.of(list)));
        List<Role> result = roleServiceImpl.getAllRoles().getBody();
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("role2", result.get(1).getName());
        Assertions.assertEquals(0, result.get(1).getUsers().size());
    }
}