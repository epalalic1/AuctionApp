package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Image;
import com.developer.auctionapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    /**
     * <p>Method that is supported with a derived query to find Image by the Product</p>
     *
     * @param product object based on which we are looking for an image
     * @return list of Image objects that are connected with the product which is sent as a parameter
     */

    @Query
    List<Image> findByProduct(Product product);

    /**
     *  <p>Method that is supported with a derived query to delete all images by the product</p>
     * @param product whose images we want to delete
     * @return number of deleted rows
     */
    @Query
    long deleteAllByProduct(Product product);
}
