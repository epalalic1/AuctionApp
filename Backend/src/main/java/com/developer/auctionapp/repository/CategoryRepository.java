package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "SELECT COUNT(*) FROM category", nativeQuery = true)
    int getNumberOfRows();
}
