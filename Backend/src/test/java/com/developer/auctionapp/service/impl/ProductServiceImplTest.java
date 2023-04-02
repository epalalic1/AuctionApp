package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.request.AddItemRequest;
import com.developer.auctionapp.dto.response.BiddersForProduct;
import com.developer.auctionapp.dto.response.ProductResponse;
import com.developer.auctionapp.dto.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * A class that contains tests for testing the methods of the ProductServiceImpl class
 */

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ProductServiceImplTest {

    @Mock
    private ProductServiceImpl productServiceImpl;

    /**
     * A method that tests a class method that returns all products
     */

    @Test
    void getAllProducts() {
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
                1L,
                new ArrayList<>(),
                1L);
        list.add(productResponse1);
        list.add(productResponse2);
        list.add(productResponse3);
        list.add(productResponse4);
        list.add(productResponse5);
        list.add(productResponse6);
        Mockito.when(productServiceImpl.getAllProducts()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final List<ProductResponse> result = productServiceImpl.getAllProducts().getBody();
        Assertions.assertEquals(6, result.size());
        Assertions.assertEquals("product4", result.get(3).getName());
        Assertions.assertEquals(50L, result.get(5).getPrice());
    }

    /**
     * A method that tests a class method that returns all newly added products
     */

    @Test
    void getNewProducts() {
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
                1L, new ArrayList<>(),
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
        Mockito.when(productServiceImpl.getAllProducts()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final List<ProductResponse> result = productServiceImpl.getAllProducts().getBody();
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("product2", result.get(1).getName());
        Assertions.assertEquals(34L, result.get(2).getPrice());
    }

    /**
     * A method that tests a class method that returns all product whose end date is about to expire
     */

    @Test
    void getLastChanceProducts() {
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
        final ProductResponse productResponse6 = new ProductResponse(
                "product6",
                ZonedDateTime.now().minusMonths(2),
                ZonedDateTime.now().plusDays(4),
                1L,
                "",
                false,
                50L,
                1L,
                1L,
                new ArrayList<>(),
                1L);
        list.add(productResponse4);
        list.add(productResponse5);
        list.add(productResponse6);
        Mockito.when(productServiceImpl.getAllProducts()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final List<ProductResponse> result = productServiceImpl.getAllProducts().getBody();
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("product6", result.get(2).getName());
        Assertions.assertEquals(50L, result.get(2).getPrice());
    }

    /**
     * A method that tests a class method that adds new product
     */

    @Test
    void addProduct() {
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
        Mockito.when(productServiceImpl.addProduct(addItemRequest)).thenReturn(response);
        final Response result = productServiceImpl.addProduct(addItemRequest);
        Assertions.assertEquals(200L, result.getStatusCode());
        Assertions.assertEquals("newMessage", result.getMessage());
    }

    /**
     * A method that tests a class method that returns all bidders for specific product
     */

    @Test
    void findBiddersForProduct() {
        final List<BiddersForProduct> list = new ArrayList<>();
        final BiddersForProduct bidder1 = new BiddersForProduct("bidder1", new Date(), 10L);
        final BiddersForProduct bidder2 = new BiddersForProduct("bidder2", new Date(), 20L);
        final BiddersForProduct bidder3 = new BiddersForProduct("bidder3", new Date(), 30L);
        list.add(bidder1);
        list.add(bidder2);
        list.add(bidder3);
        Mockito.when(productServiceImpl.findBiddersForProduct(1L)).thenReturn(ResponseEntity.of(Optional.of(list)));
        final List<BiddersForProduct> result = productServiceImpl.findBiddersForProduct(1L).getBody();
        assert result != null;
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("bidder2", result.get(1).getName());
        Assertions.assertEquals(30L, result.get(2).getAmount());
    }

    /**
     * A method that tests a class method that returns product by id
     */

    @Test
    void getProductFromId() {
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
        Mockito.when(productServiceImpl.getProductFromId(1L)).thenReturn(productResponse1);
        final ProductResponse result = productServiceImpl.getProductFromId(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("product1", result.getName());
        Assertions.assertEquals(10L, result.getPrice());
    }
}