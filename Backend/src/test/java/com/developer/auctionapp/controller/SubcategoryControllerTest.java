package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.response.SubcategoryResponse;
import com.developer.auctionapp.entity.Role;
import com.developer.auctionapp.service.ImageService;
import com.developer.auctionapp.service.RoleService;
import com.developer.auctionapp.service.SubcategoryService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SubcategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
class SubcategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SubcategoryService subcategoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findAllSubcategories() throws Exception {
        List<SubcategoryResponse> list = new ArrayList<>();
        SubcategoryResponse subcategoryResponse1 = new SubcategoryResponse(1L, "subcategory1", 1L);
        SubcategoryResponse subcategoryResponse2 = new SubcategoryResponse(2L, "subcategory2", 2L);
        SubcategoryResponse subcategoryResponse3 = new SubcategoryResponse(3L, "subcategory3", 3L);
        list.add(subcategoryResponse1);
        list.add(subcategoryResponse2);
        list.add(subcategoryResponse3);
        Mockito.when(subcategoryService.getAllSubcategories()).thenReturn(ResponseEntity.of(Optional.of(list)));
        ResultActions resultActions = mvc.perform(get("/auctionapp/subcategory/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals("subcategory1", objectMapper.convertValue(response.get(0), SubcategoryResponse.class).getName());
        Assertions.assertEquals(3L, objectMapper.convertValue(response.get(2),SubcategoryResponse.class).getCategory());
    }
}