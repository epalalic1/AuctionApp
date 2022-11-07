package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.entity.Category;
import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.repository.ProductRepository;
import com.developer.auctionapp.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {this.productRepository = productRepository;}

    @Override
    public int getNumberOfRows() {
        return productRepository.getNumberOfRows();
    }

    @Override
    public void initializeProductTable() {
        if (getNumberOfRows() != 0) {
          return;
        }
        List<Product> listOfProducts = new ArrayList<>();
        Product product1 = new Product(1L,
                "Blue shirt",
                ZonedDateTime.parse("2022-06-15T00:00:00.147Z"),
                ZonedDateTime.parse("2022-09-11T00:00:00.147Z"),
                15L,
                "details",
                false,
                15L,
                new Category(1L, "Fashion"));
        Product product2 = new Product(2L,
                "Laptop",
                ZonedDateTime.parse("2022-05-12T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-08T00:00:00.147Z"),
                150L,
                "details",
                false,
                150L,
                new Category(9L, "Computer"));
        Product product3 = new Product(3L,
                "Necklace",
                ZonedDateTime.parse("2022-04-12T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-04T00:00:00.147Z"),
                23L,
                "details",
                false,
                23L,
                new Category(3L, "Jewlery"));
        Product product4 = new Product(4L,
                "Blue Shoes",
                ZonedDateTime.parse("2022-01-01T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-04T00:00:00.147Z"),
                23L,
                "details",
                false,
                23L,
                new Category(4L, "Shoes"));
        Product product5 = new Product(5L,
                "Red shirt",
                ZonedDateTime.parse("2022-06-15T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-07T00:00:00.147Z"),
                26L,
                "details",
                false,
                26L,
                new Category(1L, "Fashion"));
        Product product6 = new Product(6L,
                "White Necklace",
                ZonedDateTime.parse("2022-02-12T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-05T00:00:00.147Z"),
                13L,
                "details",
                false,
                13L,
                new Category(3L, "Jewlery"));
        Product product7 = new Product(7L,
                "Red Shoes",
                ZonedDateTime.parse("2022-01-01T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-06T00:00:00.147Z"),
                10L,
                "details",
                false,
                10L,
                new Category(4L, "Shoes"));
        Product product8 = new Product(8L,
                "Black Shoes",
                ZonedDateTime.parse("2022-05-01T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-06T00:00:00.147Z"),
                14L,
                "details",
                false,
                14L,
                new Category(4L, "Shoes"));
        Product product9 = new Product(9L,
                "Black Big Shoes",
                ZonedDateTime.parse("2022-11-02T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-06T00:00:00.147Z"),
                14L,
                "details",
                false,
                14L,
                new Category(4L, "Shoes"));
        Product product10 = new Product(10L,
                "Laptop HP",
                ZonedDateTime.parse("2022-05-12T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-07T00:00:00.147Z"),
                15L,
                "details",
                false,
                15L,
                new Category(9L, "Computer"));
        listOfProducts.add(product1);
        listOfProducts.add(product2);
        listOfProducts.add(product3);
        listOfProducts.add(product4);
        listOfProducts.add(product5);
        listOfProducts.add(product6);
        listOfProducts.add(product7);
        listOfProducts.add(product8);
        listOfProducts.add(product9);
        listOfProducts.add(product10);
        productRepository.saveAll(listOfProducts);
    }

    @Override
    public List<Product> getAllProducts() {
       return productRepository.findAll();
    }

    @Override
    public List<Product> getNewProducts() {
        return productRepository.findByDateOfArrivingAfter(ZonedDateTime.now().minusDays(7));
    }

    @Override
    public List<Product> getLastChanceProducts() {
        return productRepository.findByEndDateBefore(ZonedDateTime.now().plusDays(7));
    }
}
