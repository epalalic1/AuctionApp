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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<BidResponse>> getAll() {
        List<Bid> lista = bidRepository.findAll();
        if (lista.size() == 0) {
            return ResponseEntity.noContent().build();
        }
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
        return ResponseEntity.of(Optional.of(result));
    }

    /**
     * The method by which we insert one offer into the database
     * @param bidRequestDto object we need to transform into Bid and insert it into database
     * @return bid object that is inserted into database
     */

    @Override
    public ResponseEntity<Bid> addBid(final BidRequestDto bidRequestDto) {
        Optional<Product> product = productRepository.findById(bidRequestDto.getProductId());
        Optional<User> user = userRepository.findById(bidRequestDto.getUserId());
        if (!product.isPresent() || !user.isPresent() ){
            return ResponseEntity.notFound().build();
        }
        Bid bid = new Bid(bidRequestDto.getAmount(),bidRequestDto.getDateOfBid(),product.get(),user.get());
        bidRepository.save(bid);
        return ResponseEntity.of(Optional.of(bid));
    }
}
