package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    /**
     * <p>Method that is supported with a derived query for finding users by email</p>
     * @param email  object based on which we are looking for a user
     * @return user that have email that is equal to the string  which is sent as a parameter
     */

    @Query
    User findByEmail(String email);

    /**
     * Method with specific query to see maximum id of data that are in database
     * @return maximum id
     */

    @Query(value = "SELECT max(id) FROM users", nativeQuery = true)
    Long getMaxId();


}
