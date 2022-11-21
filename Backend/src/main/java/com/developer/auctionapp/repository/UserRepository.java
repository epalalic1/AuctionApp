package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

    @Query(value = "SELECT COUNT(*) FROM users", nativeQuery = true)
    int getNumberOfRows();
}
