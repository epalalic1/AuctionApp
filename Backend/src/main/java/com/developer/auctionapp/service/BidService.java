package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.request.BidRequestDto;
import com.developer.auctionapp.dto.response.BidResponse;
import com.developer.auctionapp.entity.Bid;
import com.developer.auctionapp.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * An interface that contains all the methods that a BidServiceImpl service should have
 */

public interface BidService {

    ResponseEntity<List<BidResponse>> getAll();

    ResponseEntity<Bid> addBid(BidRequestDto bidRequestDto);
}
