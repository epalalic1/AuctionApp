package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query(value = "SELECT COUNT(*) FROM bid", nativeQuery = true)
    int getNumberOfRows();
}
