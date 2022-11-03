package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Category;
import com.developer.auctionapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "SELECT COUNT(*) FROM category", nativeQuery = true)
    int getNumberOfRows();
    @Modifying
    @Query(value = "insert into category (category_id, name) VALUES ('1', 'Fashion'),('2','Accessories'),('3','Jewlery'),('4','Shoes'),('5','Sportwear'),('6','Home'),('7','Electronics'),('8','Mobile'), ('9','Computer')", nativeQuery = true)
    void initializeCategoryTable();

}
