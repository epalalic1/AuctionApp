package com.developer.auctionapp.service.impl;



import com.developer.auctionapp.dto.response.BidResponseDto;
import com.developer.auctionapp.entity.Bid;
import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.entity.User;
import com.developer.auctionapp.repository.BidRepository;
import com.developer.auctionapp.repository.ProductRepository;
import com.developer.auctionapp.repository.UserRepository;
import com.developer.auctionapp.service.BidService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public BidServiceImpl(BidRepository bidRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<BidResponseDto> getAll() {
        List<Bid> lista = bidRepository.findAll();
        List<BidResponseDto> result = new ArrayList<>();
        for (Bid bid :lista){
            BidResponseDto a = new BidResponseDto(bid.getId(),bid.getAmount(),bid.getDateOfBid(),bid.getProduct().getId());
            result.add(a);
        }
        return result;
    }


}
