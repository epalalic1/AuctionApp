package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.request.BidRequestDto;
import com.developer.auctionapp.dto.response.BidResponse;
import com.developer.auctionapp.entity.Bid;

import java.util.List;

public interface BidService {

    List<BidResponse> getAll();

    Bid addBid(BidRequestDto bidRequestDto);
}
