package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.response.CategoryResponse;
import com.developer.auctionapp.service.CategoryService;
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
 * A class that contains tests for testing the Category controller
 */

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * A method that tests the controller method that returns all categories
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void getAll() throws Exception {
        final List<CategoryResponse> list = new ArrayList<>();
        final CategoryResponse firstCategory = new CategoryResponse(
                1L,
                "firstCategory",
                new ArrayList<>(),
                false);
        final CategoryResponse secondCategory = new CategoryResponse(
                2L,
                "secondCategory",
                new ArrayList<>(),
                true);
        final CategoryResponse thirdCategory = new CategoryResponse(
                3L,
                "thirdCategory",
                new ArrayList<>(),
                false);
        list.add(firstCategory);
        list.add(secondCategory);
        list.add(thirdCategory);
        Mockito.when(categoryService.getAll()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final ResultActions resultActions = mvc.perform(get("/auctionapp/category/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        final MvcResult result = resultActions.andReturn();
        final String contentAsString = result.getResponse().getContentAsString();
        final ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals("firstCategory", objectMapper.convertValue(response.get(0), CategoryResponse.class).getName());
        Assertions.assertTrue(objectMapper.convertValue(response.get(1), CategoryResponse.class).isChecked());
    }
}