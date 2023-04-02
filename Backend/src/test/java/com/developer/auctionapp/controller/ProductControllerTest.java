package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.AddItemRequest;
import com.developer.auctionapp.dto.response.BiddersForProduct;
import com.developer.auctionapp.dto.response.ProductResponse;
import com.developer.auctionapp.dto.response.Response;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * A class that contains tests for testing the Product controller
 */

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    /**
     * A method that tests the controller method that returns all products
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void findAllProducts() throws Exception {
        final List<ProductResponse> list = new ArrayList<>();
        final ProductResponse productResponse1 = new ProductResponse(
                "product1",
                ZonedDateTime.now().minusDays(4),
                ZonedDateTime.now().plusMonths(3),
                1L,
                "",
                false,
                10L,
                1L,
                1L,
                new ArrayList<>(),
                1L);
        final ProductResponse productResponse2 = new ProductResponse(
                "product2",
                ZonedDateTime.now().minusDays(5),
                ZonedDateTime.now().plusMonths(4),
                1L,
                "",
                false,
                25L,
                1L,
                1L,
                new ArrayList<>(),
                1L);
        final ProductResponse productResponse3 = new ProductResponse("product3",
                ZonedDateTime.now().minusDays(2),
                ZonedDateTime.now().plusMonths(5),
                1L,
                "",
                false,
                34L,
                1L,
                1L,
                new ArrayList<>(),
                1L);
        final ProductResponse productResponse4 = new ProductResponse(
                "product4",
                ZonedDateTime.now().minusMonths(2),
                ZonedDateTime.now().plusDays(2),
                1L,
                "",
                false,
                10L,
                1L,
                1L,
                new ArrayList<>(),
                1L);
        final ProductResponse productResponse5 = new ProductResponse(
                "product5",
                ZonedDateTime.now().minusMonths(2),
                ZonedDateTime.now().plusDays(5),
                1L,
                "",
                false,
                12L,
                1L,
                1L,
                new ArrayList<>(),
                1L);
        final ProductResponse productResponse6 = new ProductResponse(
                "product6",
                ZonedDateTime.now().minusMonths(2),
                ZonedDateTime.now().plusDays(4),
                1L,
                "",
                false,
                50L,
                1L,
                1L, new ArrayList<>(),
                1L);
        list.add(productResponse1);
        list.add(productResponse2);
        list.add(productResponse3);
        list.add(productResponse4);
        list.add(productResponse5);
        list.add(productResponse6);
        Mockito.when(productService.getAllProducts()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final ResultActions resultActions = mvc.perform(get("/auctionapp/product/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        final MvcResult result = resultActions.andReturn();
        final String contentAsString = result.getResponse().getContentAsString();
        final ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(6, response.size());
        Assertions.assertEquals("product1", objectMapper.convertValue(response.get(0), ProductResponse.class).getName());
        Assertions.assertEquals(34L, objectMapper.convertValue(response.get(2), ProductResponse.class).getPrice());
    }

    /**
     * A method that tests the controller method that returns all newly added products
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void getNewProducts() throws Exception {
        final List<ProductResponse> list = new ArrayList<>();
        final ProductResponse productResponse1 = new ProductResponse(
                "product1",
                ZonedDateTime.now().minusDays(4),
                ZonedDateTime.now().plusMonths(3),
                1L,
                "",
                false,
                10L,
                1L,
                1L,
                new ArrayList<>(),
                1L);
        final ProductResponse productResponse2 = new ProductResponse(
                "product2",
                ZonedDateTime.now().minusDays(5),
                ZonedDateTime.now().plusMonths(4),
                1L,
                "",
                false,
                25L,
                1L,
                1L,
                new ArrayList<>(),
                1L);
        final ProductResponse productResponse3 = new ProductResponse(
                "product3",
                ZonedDateTime.now().minusDays(2),
                ZonedDateTime.now().plusMonths(5),
                1L,
                "",
                false,
                34L,
                1L,
                1L,
                new ArrayList<>(),
                1L);
        list.add(productResponse1);
        list.add(productResponse2);
        list.add(productResponse3);
        Mockito.when(productService.getNewProducts()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final ResultActions resultActions = mvc.perform(get("/auctionapp/product/getNewProducts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        final MvcResult result = resultActions.andReturn();
        final String contentAsString = result.getResponse().getContentAsString();
        final ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals("product1", objectMapper.convertValue(response.get(0), ProductResponse.class).getName());
        Assertions.assertEquals(25L, objectMapper.convertValue(response.get(1), ProductResponse.class).getPrice());
    }

    /**
     * A method that tests the controller method that returns all products whose bidding end date
     * is about to expire
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void getLastChanceProducts() throws Exception {
        final List<ProductResponse> list = new ArrayList<>();
        final ProductResponse productResponse4 = new ProductResponse(
                "product4",
                ZonedDateTime.now().minusMonths(2),
                ZonedDateTime.now().plusDays(2),
                1L,
                "",
                false,
                10L,
                1L,
                1L,
                new ArrayList<>(),
                1L);
        final ProductResponse productResponse5 = new ProductResponse(
                "product5",
                ZonedDateTime.now().minusMonths(2),
                ZonedDateTime.now().plusDays(5),
                1L,
                "",
                false,
                12L,
                1L,
                1L,
                new ArrayList<>(),
                1L);
        final ProductResponse productResponse6 = new ProductResponse("product6", ZonedDateTime.now().minusMonths(2), ZonedDateTime.now().plusDays(4), 1L, "", false, 50L, 1L, 1L, new ArrayList<>(), 1L);
        list.add(productResponse4);
        list.add(productResponse5);
        list.add(productResponse6);
        Mockito.when(productService.getLastChanceProducts()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final ResultActions resultActions = mvc.perform(get("/auctionapp/product/getLastChanceProducts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        final MvcResult result = resultActions.andReturn();
        final String contentAsString = result.getResponse().getContentAsString();
        final ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals("product4", objectMapper.convertValue(response.get(0), ProductResponse.class).getName());
        Assertions.assertEquals(10L, objectMapper.convertValue(response.get(0), ProductResponse.class).getPrice());
    }

    /**
     * A method that tests the controller method that add new product
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void addItem() throws Exception {
        final AddItemRequest addItemRequest = new AddItemRequest(
                "newProduct",
                "oldCategory",
                "oldSubcategory",
                "description",
                "imageName",
                10,
                new Date(),
                new Date());
        final Response response = new Response(200L, "newMessage");
        Mockito.when(productService.addProduct(addItemRequest)).thenReturn(response);
        final String json = new ObjectMapper().writeValueAsString(addItemRequest);
        mvc.perform(MockMvcRequestBuilders.post("/auctionapp/product/addItemRequest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    /**
     * A method that tests the controller method that returns all bidders for specific product
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void getBiddersForProduct() throws Exception {
        final LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("paramName", "1");
        final List<BiddersForProduct> list = new ArrayList<>();
        final BiddersForProduct bidder1 = new BiddersForProduct("bidder1", new Date(), 10L);
        final BiddersForProduct bidder2 = new BiddersForProduct("bidder2", new Date(), 20L);
        final BiddersForProduct bidder3 = new BiddersForProduct("bidder3", new Date(), 30L);
        list.add(bidder1);
        list.add(bidder2);
        list.add(bidder3);
        Mockito.when(productService.findBiddersForProduct(1L)).thenReturn(ResponseEntity.of(Optional.of(list)));
        final ResultActions resultActions = mvc.perform(get("/auctionapp/product/getBiddersForProduct")
                        .params(requestParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        final MvcResult result = resultActions.andReturn();
        final String contentAsString = result.getResponse().getContentAsString();
        final ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals("bidder1", objectMapper.convertValue(response.get(0), BiddersForProduct.class).getName());
        Assertions.assertEquals(30L, objectMapper.convertValue(response.get(2), BiddersForProduct.class).getAmount());
    }

    /**
     * A method that tests the controller method that returns product by id
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void getProduct() throws Exception {
        final LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id", "1");
        final ProductResponse productResponse1 = new ProductResponse(
                1L,
                "product1",
                ZonedDateTime.now().minusDays(4),
                ZonedDateTime.now().plusMonths(3),
                1L,
                "",
                false,
                10L,
                1L,
                1L,
                new ArrayList<>(),
                1L);
        Mockito.when(productService.getProductFromId(1L)).thenReturn(productResponse1);
        final ResultActions resultActions = mvc.perform(get("/auctionapp/product/getProductFromId")
                        .params(requestParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        final MvcResult result = resultActions.andReturn();
        final String contentAsString = result.getResponse().getContentAsString();
        final ProductResponse response = objectMapper.readValue(contentAsString, ProductResponse.class);
        Assertions.assertNotNull(objectMapper.convertValue(response, ProductResponse.class));
        Assertions.assertEquals("product1", objectMapper.convertValue(response, ProductResponse.class).getName());
    }
}