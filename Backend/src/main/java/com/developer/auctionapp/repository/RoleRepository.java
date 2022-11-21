package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query(value = "SELECT COUNT(*) FROM roles", nativeQuery = true)
    int getNumberOfRows();
}

