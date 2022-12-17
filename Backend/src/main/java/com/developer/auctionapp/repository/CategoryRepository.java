package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    /**
     * Method with specific query to see how many data are in the Category table in the database
     * @return integer that represent number of rows in the Category table
     */

    @Query(value = "SELECT COUNT(*) FROM category", nativeQuery = true)
    int getNumberOfRows();
}
