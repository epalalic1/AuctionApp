package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.BidRequestDto;
import com.developer.auctionapp.dto.response.BidResponse;
import com.developer.auctionapp.entity.*;
import com.developer.auctionapp.service.BidService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BidController.class)
@AutoConfigureMockMvc(addFilters = false)
class BidControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean private BidService bidService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll() throws Exception {
        List<BidResponse> list = new ArrayList<>();
        BidResponse first = new BidResponse(1L, 1L, ZonedDateTime.now().minusDays(5), 1L, 1L);
        BidResponse second = new BidResponse(2L, 10L, ZonedDateTime.now().minusDays(5), 1L, 2L);
        BidResponse third = new BidResponse(3L, 100L, ZonedDateTime.now().minusDays(5), 1L, 3L);
        list.add(first);
        list.add(second);
        list.add(third);
        Mockito.when(bidService.getAll()).thenReturn(ResponseEntity.of(Optional.of(list)));
        ResultActions resultActions = mvc.perform(get("/auctionapp/bid/getAll")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals(1L, objectMapper.convertValue(response.get(0),BidResponse.class).getId());
        Assertions.assertEquals(10L, objectMapper.convertValue(response.get(1),BidResponse.class).getAmount());
        Assertions.assertEquals(3L,objectMapper.convertValue(response.get(2),BidResponse.class).getUserId());
    }

    @Test
    void addBid() throws Exception {
        BidRequestDto bidRequestDto = new BidRequestDto(1L, 10L, null, 1L,1L);
        Category category = new Category("newCategory");
        Subcategory subcategory = new Subcategory("newSubcategory", category);
        User firstUser = new User(1L, "user1","user1","user1","user1","user1","user1",ZonedDateTime.now().minusYears(20));
        User secondUser = new User(2L,"user2","user2","user2","user2","user2","user2",ZonedDateTime.now().minusYears(20));
        Product product = new Product(1L,"product1", ZonedDateTime.now(),ZonedDateTime.now().plusMonths(1),1l,"a",false,1l,subcategory,firstUser);
        Bid bid = new Bid (bidRequestDto.getAmount(),bidRequestDto.getDateOfBid(), product,secondUser);
        Mockito.when(bidService.addBid(bidRequestDto)).thenReturn(ResponseEntity.of(Optional.of(bid)));
        String jsonObject = new ObjectMapper().writeValueAsString(bidRequestDto);
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                        .post("/auctionapp/bid/addBid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bidRequestDto))
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk());
    }
}