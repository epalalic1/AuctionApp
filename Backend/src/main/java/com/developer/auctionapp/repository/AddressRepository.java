package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * Method with specific query to see how many data are in the Address table in the database
     * @return integer that represent number of rows in the Address table
     */

    @Query(value = "SELECT COUNT(*) FROM address", nativeQuery = true)
    int getNumberOfRows();
}
