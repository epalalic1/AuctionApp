package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.PaymentRequest;
import com.developer.auctionapp.dto.response.PaymentResponse;
import com.developer.auctionapp.entity.Role;
import com.developer.auctionapp.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.exception.StripeException;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CreatePaymentController.class)
@AutoConfigureMockMvc(addFilters = false)
class CreatePaymentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPayment() throws Exception {
        PaymentRequest paymentRequest = new PaymentRequest("eur","description",180,"mail@gmail.com","12345E",1L);
        PaymentResponse paymentResponse = new PaymentResponse("clientSecretABC");
        Mockito.when(paymentService.charge(paymentRequest)).thenReturn(paymentResponse);
        String jsonObject = new ObjectMapper().writeValueAsString(paymentRequest);
        ResultActions resultActions =mvc.perform( MockMvcRequestBuilders
                        .post("/auctionapp/createPayment/")
                        .content(jsonObject)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}