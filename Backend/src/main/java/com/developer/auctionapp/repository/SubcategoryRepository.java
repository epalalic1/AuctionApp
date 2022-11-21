package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory,Long> {

    @Query(value = "SELECT COUNT(*) FROM subcategory", nativeQuery = true)
    int getNumberOfRows();
}
