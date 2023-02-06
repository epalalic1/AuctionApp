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

/**
 * <p>Class that implements BidService interface and we use it to comunicate with the database</p>
 */

@Service
@Transactional
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public BidServiceImpl(
            final BidRepository bidRepository,
            final ProductRepository productRepository,
            final UserRepository userRepository) {
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    /**
     * The method used to get all bids from database and transform them into Data Transform Objects
     * @return list of Data Transform Objects which each of them represent one Bid
     */

    @Override
    public List<BidResponse> getAll() {
        List<Bid> lista = bidRepository.findAll();
        List<BidResponse> result = new ArrayList<>();
        for (Bid bid : lista) {
            final BidResponse a = new BidResponse(
                    bid.getId(),
                    bid.getAmount(),
                    bid.getDateOfBid(),
                    bid.getProduct().getId(),
                    bid.getUser().getId());
            result.add(a);
        }
        return result;
    }

    /**
     * The method by which we insert one offer into the database
     * @param bidRequestDto object we need to transform into Bid and insert it into database
     * @return bid object that is inserted into database
     */

    @Override
    public Bid addBid(final BidRequestDto bidRequestDto) {
        Product product = productRepository.findById(bidRequestDto.getProductId()).get();
        User user = userRepository.findById(bidRequestDto.getUserId()).get();
        Bid bid = new Bid(bidRequestDto.getAmount(),bidRequestDto.getDateOfBid(),product,user);
        bidRepository.save(bid);
        return bid;
    }
}
