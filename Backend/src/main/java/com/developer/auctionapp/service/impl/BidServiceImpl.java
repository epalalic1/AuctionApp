package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.request.BidRequestDto;
import com.developer.auctionapp.dto.response.BidResponse;
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
    public List<BidResponse> getAll() {
        List<Bid> lista = bidRepository.findAll();
        List<BidResponse> result = new ArrayList<>();
        for (Bid bid : lista) {
            final BidResponse a = new BidResponse(
                    bid.getId(),
                    bid.getAmount(),
                    bid.getDateOfBid(),
                    bid.getProduct().getId());
            result.add(a);
        }
        return result;
    }

    @Override
    public Bid addBid(BidRequestDto bidRequestDto) {
        long id = bidRepository.getNumberOfRows() + 1;
        Product product = productRepository.findById(bidRequestDto.getProductId()).get();
        User user = userRepository.findById(bidRequestDto.getUserId()).get();
        Bid bid = new Bid(id,bidRequestDto.getAmount(),bidRequestDto.getDateOfBid(),product,user);
        bidRepository.save(bid);
        return bid;
    }
}
