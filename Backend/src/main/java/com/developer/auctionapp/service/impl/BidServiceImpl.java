package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.response.BidResponse;
import com.developer.auctionapp.entity.Bid;
import com.developer.auctionapp.repository.BidRepository;
import com.developer.auctionapp.service.BidService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;

    public BidServiceImpl(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Override
    public List<BidResponse> getAll() {
        List<Bid> lista = bidRepository.findAll();
        List<BidResponse> result = new ArrayList<>();
        for (Bid bid :lista){
            BidResponse a = new BidResponse(bid.getId(),bid.getAmount(),bid.getDateOfBid(),bid.getProduct().getId());
            result.add(a);
        }
        return result;
    }
}
