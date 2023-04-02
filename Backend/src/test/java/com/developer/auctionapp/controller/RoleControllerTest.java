package com.developer.auctionapp.controller;

import com.developer.auctionapp.entity.Role;
import com.developer.auctionapp.service.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * A class that contains tests for testing the Role controller
 */

@RunWith(SpringRunner.class)
@WebMvcTest(RoleController.class)
@AutoConfigureMockMvc(addFilters = false)
class RoleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoleService roleService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * A method that tests the controller method that returns all roles
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void findAllRoles() throws Exception {
        final List<Role> list = new ArrayList<>();
        final Role role1 = new Role("role1");
        final Role role2 = new Role("role2");
        final Role role3 = new Role("role3");
        list.add(role1);
        list.add(role2);
        list.add(role3);
        Mockito.when(roleService.getAllRoles()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final ResultActions resultActions = mvc.perform(get("/auctionapp/role/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        final MvcResult result = resultActions.andReturn();
        final String contentAsString = result.getResponse().getContentAsString();
        final ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals("role1", objectMapper.convertValue(response.get(0), Role.class).getName());
        Assertions.assertEquals(0, objectMapper.convertValue(response.get(1), Role.class).getUsers().size());
    }
}