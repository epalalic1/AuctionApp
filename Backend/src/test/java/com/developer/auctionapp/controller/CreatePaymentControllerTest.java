package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.PaymentRequest;
import com.developer.auctionapp.dto.response.PaymentResponse;
import com.developer.auctionapp.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * A class that contains tests for testing the CreatePayment controller
 */

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

    /**
     * A method that tests the controller method that enables payment
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void createPayment() throws Exception {
        final PaymentRequest paymentRequest = new PaymentRequest(
                "eur",
                "description",
                180,
                "mail@gmail.com",
                "12345E",
                1L);
        PaymentResponse paymentResponse = new PaymentResponse("clientSecretABC");
        Mockito.when(paymentService.charge(paymentRequest)).thenReturn(paymentResponse);
        final String jsonObject = new ObjectMapper().writeValueAsString(paymentRequest);
        final ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                        .post("/auctionapp/createPayment/")
                        .content(jsonObject)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}