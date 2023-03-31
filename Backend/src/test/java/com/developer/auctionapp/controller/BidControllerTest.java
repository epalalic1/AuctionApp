package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.BidRequestDto;
import com.developer.auctionapp.dto.response.BidResponse;
import com.developer.auctionapp.entity.*;
import com.developer.auctionapp.service.impl.BidServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * A class that contains tests for testing the Bid controller
 */

@RunWith(SpringRunner.class)
@WebMvcTest(BidController.class)
@AutoConfigureMockMvc(addFilters = false)
class BidControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BidServiceImpl bidServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BidController bidController;


    /**
     * A method that tests the controller method that returns all bids
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void getAll() throws Exception {
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
        when(bidServiceImpl.getAll()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final ResultActions resultActions = mvc.perform(get("/auctionapp/bid/getAll")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
        final MvcResult result = resultActions.andReturn();
        final String contentAsString = result.getResponse().getContentAsString();
        final ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals(1L, objectMapper.convertValue(response.get(0), BidResponse.class).getId());
        Assertions.assertEquals(10L, objectMapper.convertValue(response.get(1), BidResponse.class).getAmount());
        Assertions.assertEquals(3L, objectMapper.convertValue(response.get(2), BidResponse.class).getUserId());
    }

    /**
     * A method that tests the controller method that adds a bid to the database
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void addBid() throws Exception {
        final BidRequestDto bidRequestDto = new BidRequestDto(
                1L,
                10L,
                null,
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
                null,
                null,
                1l,
                "a",
                false,
                1l,
                subcategory,
                firstUser);
        final Bid bid = new Bid(bidRequestDto.getAmount(), bidRequestDto.getDateOfBid(), product, secondUser);
        when(bidServiceImpl.addBid(bidRequestDto)).thenReturn(ResponseEntity.of(Optional.of(bid)));
        final String json = new ObjectMapper().writeValueAsString(bidRequestDto);
        mvc.perform(MockMvcRequestBuilders.post("/auctionapp/bid/addBid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}