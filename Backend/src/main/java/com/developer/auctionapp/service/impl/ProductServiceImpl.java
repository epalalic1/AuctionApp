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

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ImageRepository imageRepository;

    public ProductServiceImpl(ProductRepository productRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

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
                    imageNames);
            list.add(item);
        }
        return list;
    }

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
                    imageNames);
            list.add(item);
        }
        return list;
    }

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
                    imageNames);
            list.add(item);
        }
        return list;
    }
}
