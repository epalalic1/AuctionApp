package com.developer.auctionapp.controller;

import com.developer.auctionapp.service.InitializeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * A class that contains tests for testing the Initialize controller
 */

@RunWith(SpringRunner.class)
@WebMvcTest(InitializeController.class)
@AutoConfigureMockMvc(addFilters = false)
class InitializeControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InitializeService initializeService;

    /**
     * A method that tests the controller method that initialize the database
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void initialize() throws Exception {
        final ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(initializeService.initializeDatabase()).thenReturn(responseEntity);
        ResultActions resultActions = mvc.perform(get("/auctionapp/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}