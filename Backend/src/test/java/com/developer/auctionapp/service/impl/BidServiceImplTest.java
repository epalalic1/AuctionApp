package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.request.BidRequestDto;
import com.developer.auctionapp.dto.response.BidResponse;
import com.developer.auctionapp.entity.*;
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
import java.util.List;
import java.util.Optional;

/**
 * A class that contains tests for testing the methods of the BidServiceImpl class
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class BidServiceImplTest {


    @Mock
    private BidServiceImpl bidServiceImpl;

    /**
     * A method that tests a class method that returns all bids
     */

    @Test
    void getAll() {
        final List<BidResponse> list = new ArrayList<>();
        final BidResponse first = new BidResponse(
                1L,
                1L,
                ZonedDateTime.now().minusDays(5),
                1L,
                1L);
        final BidResponse second = new BidResponse(
                2L,
                10L,
                ZonedDateTime.now().minusDays(5),
                1L,
                2L);
        final BidResponse third = new BidResponse(
                3L,
                100L,
                ZonedDateTime.now().minusDays(5),
                1L,
                3L);
        list.add(first);
        list.add(second);
        list.add(third);
        Mockito.when(bidServiceImpl.getAll()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final List<BidResponse> result = bidServiceImpl.getAll().getBody();
        Assertions.assertEquals(3, result.size());
    }

    /**
     * A method that tests a class method that adds new bid to the  database
     */

    @Test
    void addBid() {
        final BidRequestDto bidRequestDto = new BidRequestDto(
                1L,
                10L,
                ZonedDateTime.now(),
                1L,
                1L);
        final Category category = new Category("newCategory");
        final Subcategory subcategory = new Subcategory("newSubcategory", category);
        final User firstUser = new User(
                1L,
                "user1",
                "user1",
                "user1",
                "user1",
                "user1",
                "user1",
                ZonedDateTime.now().minusYears(20));
        final User secondUser = new User(
                2L,
                "user2",
                "user2",
                "user2",
                "user2",
                "user2",
                "user2",
                ZonedDateTime.now().minusYears(20));
        final Product product = new Product(
                1L,
                "product1",
                ZonedDateTime.now(),
                ZonedDateTime.now().plusMonths(1),
                1l,
                "a",
                false,
                1l,
                subcategory,
                firstUser);
        final Bid bid = new Bid(bidRequestDto.getAmount(), bidRequestDto.getDateOfBid(), product, secondUser);
        Mockito.when(bidServiceImpl.addBid(bidRequestDto)).thenReturn(ResponseEntity.of(Optional.of(bid)));
        final Bid result = bidServiceImpl.addBid(bidRequestDto).getBody();
        assert result != null;
        Assertions.assertEquals(10L, result.getAmount());
        Assertions.assertEquals("newCategory", bid.getProduct().getSubcategory().getCategory().getName());
        Assertions.assertEquals("user2", bid.getUser().getName());
    }
}