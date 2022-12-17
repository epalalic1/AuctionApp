package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    /**
     * Method with specific query to see how many data are in the Bid table in the database
     * @return integer that represent number of rows in the Bid table
     */

    @Query(value = "SELECT COUNT(*) FROM bid", nativeQuery = true)
    int getNumberOfRows();
}
