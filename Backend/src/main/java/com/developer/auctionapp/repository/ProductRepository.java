package com.developer.auctionapp.repository;


import com.developer.auctionapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT COUNT(*) FROM product", nativeQuery = true)
    int getNumberOfRows();
    @Modifying
    @Query(value = "insert into product (product_id,name,date_of_arriving,end_date,start_price,details,status,price,category_category_id) VALUES ('1', 'Blue shirt','2022-06-15','2022-09-11','15','details','0','15','1'), ('2','Laptop','2022-05-12','2022-11-08','150','details','0','150','9'),('3','Necklace','2022-04-12','2022-11-4','23','details','0','23','3'), ('4','Blue Shoes','2022-01-01','2022-11-4','23','detsils','0','23','4'), ('5', 'Red shirt','2022-06-15','2022-11-07','26','details','0','26','1'), ('6','White Necklace', '2022-02-12','2022-11-5','13','details','0','13','3'), ('7','Red Shoes','2022-01-01','2022-11-06','10','detsils','0','10','4'), ('8','Black Shoes','2022-05-01','2022-11-06','14','detsils','0','14','4'),('9','Black Big Shoes','2022-11-02','2023-11-06','14','detsils','0','14','4'), ('10','Laptop HP','2022-05-12','2022-11-07','15','details','0','15','9')", nativeQuery = true)
    void initializeProductTable();

    @Query(value = "SELECT * FROM product WHERE date_of_arriving BETWEEN (NOW() - INTERVAL '7 day') AND NOW()",nativeQuery = true)
    List<Product> getNewProducts();
    @Query(value = "SELECT * FROM product WHERE end_date BETWEEN  current_date and  (current_date + INTERVAL '7 day') ",nativeQuery = true)
    List<Product> getLastChanceProducts();

}
