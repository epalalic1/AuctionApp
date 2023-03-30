package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.response.BiddersForProduct;
import com.developer.auctionapp.dto.response.ProductResponse;
import com.developer.auctionapp.entity.Role;
import com.developer.auctionapp.service.ProductService;
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
import org.springframework.util.LinkedMultiValueMap;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean private ProductService productService;

    @Test
    void findAllProducts() throws Exception {
        List<ProductResponse> list = new ArrayList<>();
        ProductResponse productResponse1 = new ProductResponse("product1", ZonedDateTime.now().minusDays(4),ZonedDateTime.now().plusMonths(3),1L,"",false,10L,1L,1L,new ArrayList<>(),1L);
        ProductResponse productResponse2 = new ProductResponse("product2", ZonedDateTime.now().minusDays(5),ZonedDateTime.now().plusMonths(4),1L,"",false,25L,1L,1L,new ArrayList<>(),1L);
        ProductResponse productResponse3 = new ProductResponse("product3", ZonedDateTime.now().minusDays(2),ZonedDateTime.now().plusMonths(5),1L,"",false,34L,1L,1L,new ArrayList<>(),1L);
        ProductResponse productResponse4 = new ProductResponse("product4", ZonedDateTime.now().minusMonths(2),ZonedDateTime.now().plusDays(2),1L,"",false,10L,1L,1L,new ArrayList<>(),1L);
        ProductResponse productResponse5 = new ProductResponse("product5", ZonedDateTime.now().minusMonths(2),ZonedDateTime.now().plusDays(5),1L,"",false,12L,1L,1L,new ArrayList<>(),1L);
        ProductResponse productResponse6 = new ProductResponse("product6", ZonedDateTime.now().minusMonths(2),ZonedDateTime.now().plusDays(4),1L,"",false,50L,1L,1L,new ArrayList<>(),1L);
        list.add(productResponse1);
        list.add(productResponse2);
        list.add(productResponse3);
        list.add(productResponse4);
        list.add(productResponse5);
        list.add(productResponse6);
        Mockito.when(productService.getAllProducts()).thenReturn(ResponseEntity.of(Optional.of(list)));
        ResultActions resultActions = mvc.perform(get("/auctionapp/product/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(6, response.size());
        Assertions.assertEquals("product1", objectMapper.convertValue(response.get(0), ProductResponse.class).getName());
        Assertions.assertEquals(34L, objectMapper.convertValue(response.get(2),ProductResponse.class).getPrice());
    }

    @Test
    void getNewProducts() throws Exception {
        List<ProductResponse> list = new ArrayList<>();
        ProductResponse productResponse1 = new ProductResponse("product1", ZonedDateTime.now().minusDays(4),ZonedDateTime.now().plusMonths(3),1L,"",false,10L,1L,1L,new ArrayList<>(),1L);
        ProductResponse productResponse2 = new ProductResponse("product2", ZonedDateTime.now().minusDays(5),ZonedDateTime.now().plusMonths(4),1L,"",false,25L,1L,1L,new ArrayList<>(),1L);
        ProductResponse productResponse3 = new ProductResponse("product3", ZonedDateTime.now().minusDays(2),ZonedDateTime.now().plusMonths(5),1L,"",false,34L,1L,1L,new ArrayList<>(),1L);
        list.add(productResponse1);
        list.add(productResponse2);
        list.add(productResponse3);
        Mockito.when(productService.getNewProducts()).thenReturn(ResponseEntity.of(Optional.of(list)));
        ResultActions resultActions = mvc.perform(get("/auctionapp/product/getNewProducts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals("product1", objectMapper.convertValue(response.get(0), ProductResponse.class).getName());
        Assertions.assertEquals(25L, objectMapper.convertValue(response.get(1),ProductResponse.class).getPrice());
    }

    @Test
    void getLastChanceProducts() throws Exception {
        List<ProductResponse> list = new ArrayList<>();
        ProductResponse productResponse4 = new ProductResponse("product4", ZonedDateTime.now().minusMonths(2),ZonedDateTime.now().plusDays(2),1L,"",false,10L,1L,1L,new ArrayList<>(),1L);
        ProductResponse productResponse5 = new ProductResponse("product5", ZonedDateTime.now().minusMonths(2),ZonedDateTime.now().plusDays(5),1L,"",false,12L,1L,1L,new ArrayList<>(),1L);
        ProductResponse productResponse6 = new ProductResponse("product6", ZonedDateTime.now().minusMonths(2),ZonedDateTime.now().plusDays(4),1L,"",false,50L,1L,1L,new ArrayList<>(),1L);
        list.add(productResponse4);
        list.add(productResponse5);
        list.add(productResponse6);
        Mockito.when(productService.getLastChanceProducts()).thenReturn(ResponseEntity.of(Optional.of(list)));
        ResultActions resultActions = mvc.perform(get("/auctionapp/product/getLastChanceProducts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals("product4", objectMapper.convertValue(response.get(0), ProductResponse.class).getName());
        Assertions.assertEquals(10L, objectMapper.convertValue(response.get(0),ProductResponse.class).getPrice());
    }

    @Test
    void addItem() {
    }

    @Test
    void getBiddersForProduct() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("paramName", "1");
        List<BiddersForProduct> list = new ArrayList<>();
        BiddersForProduct bidder1 = new BiddersForProduct("bidder1", new Date(), 10L);
        BiddersForProduct bidder2 = new BiddersForProduct("bidder2", new Date(), 20L);
        BiddersForProduct bidder3 = new BiddersForProduct("bidder3", new Date(), 30L);
        list.add(bidder1);
        list.add(bidder2);
        list.add(bidder3);
        Mockito.when(productService.findBiddersForProduct(1L)).thenReturn(ResponseEntity.of(Optional.of(list)));
        ResultActions resultActions = mvc.perform(get("/auctionapp/product/getBiddersForProduct")
                        .params(requestParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals("bidder1", objectMapper.convertValue(response.get(0), BiddersForProduct.class).getName());
        Assertions.assertEquals(30L, objectMapper.convertValue(response.get(2),BiddersForProduct.class).getAmount());
    }

    @Test
    void getProduct() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id", "1");
        ProductResponse productResponse1 = new ProductResponse(1L, "product1", ZonedDateTime.now().minusDays(4),ZonedDateTime.now().plusMonths(3),1L,"",false,10L,1L,1L,new ArrayList<>(),1L);
        Mockito.when(productService.getProductFromId(1L)).thenReturn(productResponse1);
        ResultActions resultActions = mvc.perform(get("/auctionapp/product/getProductFromId")
                        .params(requestParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ProductResponse response = objectMapper.readValue(contentAsString, ProductResponse.class);
        Assertions.assertNotNull(objectMapper.convertValue(response, ProductResponse.class));
        Assertions.assertEquals("product1", objectMapper.convertValue(response, ProductResponse.class).getName());
    }
}