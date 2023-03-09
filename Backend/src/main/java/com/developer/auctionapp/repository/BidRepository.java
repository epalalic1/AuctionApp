package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Bid;
import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    /**
     * A method that returns all bids based on the product
     * @param product The product on the basis of which we are asking for bids
     * @return list of bids on product sent as parameter
     */

    @Query
    List<Bid> findByProduct (Product product);
}
