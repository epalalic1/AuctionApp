package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    /**
     * A method that returns a category based on name
     * @param name by which we are looking for the category
     * @return cateogory with specific name
     */

    @Query
    Category findByName(String name);
}