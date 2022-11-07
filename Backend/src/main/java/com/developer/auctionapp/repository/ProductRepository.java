package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByDateOfArrivingAfter(ZonedDateTime date);

    List<Product> findByEndDateBefore(ZonedDateTime date);

    @Query(value = "SELECT COUNT(*) FROM product", nativeQuery = true)
    int getNumberOfRows();
}
