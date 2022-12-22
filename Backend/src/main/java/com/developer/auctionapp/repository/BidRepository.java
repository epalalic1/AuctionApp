package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Bid;
import com.developer.auctionapp.entity.User;
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

    /**
     * <p>Method that is supported with a derived query to delete all bids by the user</p>
     * @param user whose bids we want to delete
     * @return number of deleted rows
     */

    @Query
    long deleteAllByUser(User user);
}
