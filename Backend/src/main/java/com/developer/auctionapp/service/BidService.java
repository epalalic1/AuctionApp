package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.response.BidResponseDto;
import com.developer.auctionapp.entity.Bid;

import java.util.List;

public interface BidService {

    List<BidResponseDto> getAll();
}