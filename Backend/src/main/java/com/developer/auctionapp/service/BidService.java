package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.response.BidResponse;
import java.util.List;

public interface BidService {

    List<BidResponse> getAll();
}
