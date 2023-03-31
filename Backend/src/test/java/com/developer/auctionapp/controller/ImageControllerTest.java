package com.developer.auctionapp.controller;

import com.developer.auctionapp.entity.Image;
import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.entity.Role;
import com.developer.auctionapp.service.ImageService;
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
 * A class that contains tests for testing the Image controller
 */

@RunWith(SpringRunner.class)
@WebMvcTest(ImageController.class)
@AutoConfigureMockMvc(addFilters = false)
class ImageControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ImageService imageService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * A method that tests the controller method that returns all images
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void getAll() throws Exception {
        final List<Image> list = new ArrayList<>();
        final Image image1 = new Image("image1", new Product());
        final Image image2 = new Image("image2", new Product());
        final Image image3 = new Image("image3", new Product());
        final Image image4 = new Image("image4", new Product());
        list.add(image1);
        list.add(image2);
        list.add(image3);
        list.add(image4);
        Mockito.when(imageService.getAll()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final ResultActions resultActions = mvc.perform(get("/auctionapp/image/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        final MvcResult result = resultActions.andReturn();
        final String contentAsString = result.getResponse().getContentAsString();
        final ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(4, response.size());
        Assertions.assertEquals("image2", objectMapper.convertValue(response.get(1), Role.class).getName());
        Assertions.assertEquals("image4", objectMapper.convertValue(response.get(3), Role.class).getName());
    }
}