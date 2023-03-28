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


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class BidServiceImplTest {


    @Mock
    private BidServiceImpl bidServiceImpl;


    @Test
    void getAll() {
        List<BidResponse> list = new ArrayList<>();
        BidResponse first = new BidResponse(1L, 1L, ZonedDateTime.now().minusDays(5), 1L, 1L);
        BidResponse second = new BidResponse(2L, 10L, ZonedDateTime.now().minusDays(5), 1L, 2L);
        BidResponse third = new BidResponse(3L, 100L, ZonedDateTime.now().minusDays(5), 1L, 3L);
        list.add(first);
        list.add(second);
        list.add(third);
        Mockito.when(bidServiceImpl.getAll()).thenReturn(ResponseEntity.of(Optional.of(list)));
        List<BidResponse> result = bidServiceImpl.getAll().getBody();
        Assertions.assertEquals(3, result.size());
    }

    @Test
    void addBid() {
        BidRequestDto bidRequestDto = new BidRequestDto(1L, 10L,ZonedDateTime.now(), 1L,1L);
        Category category = new Category("newCategory");
        Subcategory subcategory = new Subcategory("newSubcategory", category);
        User firstUser = new User(1L, "user1","user1","user1","user1","user1","user1",ZonedDateTime.now().minusYears(20));
        User secondUser = new User(2L,"user2","user2","user2","user2","user2","user2",ZonedDateTime.now().minusYears(20));
        Product product = new Product(1L,"product1", ZonedDateTime.now(),ZonedDateTime.now().plusMonths(1),1l,"a",false,1l,subcategory,firstUser);
        Bid bid = new Bid (bidRequestDto.getAmount(),bidRequestDto.getDateOfBid(), product,secondUser);
        Mockito.when(bidServiceImpl.addBid(bidRequestDto)).thenReturn(ResponseEntity.of(Optional.of(bid)));
        Bid result = bidServiceImpl.addBid(bidRequestDto).getBody();
        assert result != null;
        Assertions.assertEquals(10L, result.getAmount());
        Assertions.assertEquals("newCategory",bid.getProduct().getSubcategory().getCategory().getName());
        Assertions.assertEquals("user2",bid.getUser().getName());
    }
}