package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.response.ProductResponseDto;
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

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ImageRepository imageRepository;

    public ProductServiceImpl(ProductRepository productRepository, ImageRepository imageRepository) {this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> a = productRepository.findAll();
        List<ProductResponseDto> lista = new ArrayList<>();
        List<Image> imagesList = imageRepository.findAll();
        for (Product res : a) {
            String imgName = "";
            for(Image img :imagesList) {
                if (img.getProduct().getId() == res.getId()) {
                    imgName = img.getName();
                }
            }
            ProductResponseDto item = new ProductResponseDto(res.getId(),
                    res.getName(),res.getDateOfArriving(),res.getEndDate(),
                    res.getStartPrice(),res.getDetails(),res.getStatus(),
                    res.getPrice(),res.getSubcategory().getId(),res.getUser().getId(),imgName);
            lista.add(item);
        }
        return lista;
    }

    @Override
    public List<ProductResponseDto> getNewProducts() {
        List<Product> a =  productRepository.findByDateOfArrivingAfter(ZonedDateTime.now().minusDays(7));
        List<ProductResponseDto> lista = new ArrayList<>();
        List<Image> imagesList = imageRepository.findAll();
        for (Product res : a) {
            String imgName = "";
            for(Image img :imagesList) {
                if (img.getProduct().getId() == res.getId()) {
                    imgName = img.getName();
                }
            }
            ProductResponseDto item = new ProductResponseDto(res.getId(),
                    res.getName(),res.getDateOfArriving(),res.getEndDate(),
                    res.getStartPrice(),res.getDetails(),res.getStatus(),
                    res.getPrice(),res.getSubcategory().getId(),res.getUser().getId(),imgName);
            lista.add(item);
        }
        return lista;
    }

    @Override
    public List<ProductResponseDto> getLastChanceProducts() {
        List<Product> a = productRepository.findByEndDateBefore(ZonedDateTime.now().plusDays(7));
        List<ProductResponseDto> lista = new ArrayList<>();
        List<Image> imagesList = imageRepository.findAll();
        for (Product res : a) {
            String imgName = "";
            for(Image img :imagesList) {
                if (img.getProduct().getId() == res.getId()) {
                    imgName = img.getName();
                }
            }
            ProductResponseDto item = new ProductResponseDto(res.getId(),
                    res.getName(),res.getDateOfArriving(),res.getEndDate(),
                    res.getStartPrice(),res.getDetails(),res.getStatus(),
                    res.getPrice(),res.getSubcategory().getId(),res.getUser().getId(),imgName);
            lista.add(item);
        }
        return lista;
    }
}
