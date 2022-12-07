package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    /**
     * Method with specific query to see how many data are in the User table in the database
     * @return integer that represent number of rows in the User table
     */

    @Query(value = "SELECT COUNT(*) FROM users", nativeQuery = true)
    int getNumberOfRows();
}
