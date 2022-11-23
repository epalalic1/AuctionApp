package com.developer.auctionapp.repository;


import com.developer.auctionapp.entity.Image;
import com.developer.auctionapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {

    @Query(value = "SELECT COUNT(*) FROM image", nativeQuery = true)
    int getNumberOfRows();

    @Query
    List<Image> findByProduct(Product product);
}
