package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.response.ProductResponse;
import com.developer.auctionapp.entity.Image;
import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.repository.ImageRepository;
import com.developer.auctionapp.repository.ProductRepository;
import com.developer.auctionapp.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Class that implements ProductService interface and we use it to comunicate with the database</p>
 */

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ImageRepository imageRepository;

    public ProductServiceImpl(final ProductRepository productRepository, final ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

    /**
     * The method used to get all products from database and transform them into Data Transform Objects
     * @return list of Data Transform Objects which each of them represent one Product
     */

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> listOfProducts = productRepository.findAll();
        List<ProductResponse> list = new ArrayList<>();
        for (Product res : listOfProducts) {
            List<Image> images = imageRepository.findByProduct(res);
            List<String> imageNames = images.stream().map( Image::getName ).collect( Collectors.toList() );
            final ProductResponse item = new ProductResponse(
                    res.getId(),
                    res.getName(),
                    res.getDateOfArriving(),
                    res.getEndDate(),
                    res.getStartPrice(),
                    res.getDetails(),
                    res.getStatus(),
                    res.getPrice(),
                    res.getSubcategory().getId(),
                    res.getUser().getId(),
                    imageNames,
                    res.getSubcategory().getCategory().getId());
            list.add(item);
        }
        return list;
    }

    /**
     * The method used to get all products whose arrival date is not older than 7 days compared to today's date
     * @return list of Data Transform Objects which each of them represent one Product
     */

    @Override
    public List<ProductResponse> getNewProducts() {
        List<Product> listOfProducts = productRepository.findByDateOfArrivingAfter(ZonedDateTime.now().minusDays(7));
        List<ProductResponse> list = new ArrayList<>();
        for (Product res : listOfProducts) {
            List<Image> images = imageRepository.findByProduct(res);
            List<String> imageNames = images.stream().map( Image::getName ).collect( Collectors.toList() );
            final ProductResponse item = new ProductResponse(
                    res.getId(),
                    res.getName(),
                    res.getDateOfArriving(),
                    res.getEndDate(),
                    res.getStartPrice(),
                    res.getDetails(),
                    res.getStatus(),
                    res.getPrice(),
                    res.getSubcategory().getId(),
                    res.getUser().getId(),
                    imageNames,
                    res.getSubcategory().getCategory().getId());
            list.add(item);
        }
        return list;
    }

    /**
     * The method used to get all products whose end date lasts another seven days compared to today's date
     * @return list of Data Transform Objects which each of them represent one Product
     */

    @Override
    public List<ProductResponse> getLastChanceProducts() {
        List<Product> listOfProducts = productRepository.findByEndDateBefore(ZonedDateTime.now().plusDays(7));
        List<ProductResponse> list = new ArrayList<>();
        for (Product res : listOfProducts) {
            List<Image> images = imageRepository.findByProduct(res);
            List<String> imageNames = images.stream().map( Image::getName ).collect( Collectors.toList() );
            final ProductResponse item = new ProductResponse(
                    res.getId(),
                    res.getName(),
                    res.getDateOfArriving(),
                    res.getEndDate(),
                    res.getStartPrice(),
                    res.getDetails(),
                    res.getStatus(),
                    res.getPrice(),
                    res.getSubcategory().getId(),
                    res.getUser().getId(),
                    imageNames,
                    res.getSubcategory().getCategory().getId());
            list.add(item);
        }
        return list;
    }
}
