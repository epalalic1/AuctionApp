package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * <p>Method that is supported with a derived query to find Products by the date of arriving</p>
     *
     * @param date based on which we will filter the products
     * @return list of products whose arrival date is after the date sent as a parameter
     */

    List<Product> findByDateOfArrivingAfter(ZonedDateTime date);

    /**
     * <p>Method that is supported with a derived query to find Products by the end date for bidding</p>
     *
     * @param date based on which we will filter the products
     * @return list of products whose end date is before the date sent as a parameter
     */

    List<Product> findByEndDateBefore(ZonedDateTime date);

    /**
     * Method with specific query to see how many data are in the Product table in the database
     * @return integer that represent number of rows in the Product table
     */

    @Query(value = "SELECT COUNT(*) FROM product", nativeQuery = true)
    int getNumberOfRows();

    /**
     *  <p>Method that is supported with a derived query to delete all products by the user</p>
     * @param user whose product we want to delete
     * @return number of deleted rows
     */

    @Query
    long deleteAllByUser(User user);

    /**
     * <p>Method that is supported with a derived query to find products by the user</p>
     * @param user whose products we want to get
     * @return list of user's products
     */

    @Query
    List<Product> findByUser(User user);
}
