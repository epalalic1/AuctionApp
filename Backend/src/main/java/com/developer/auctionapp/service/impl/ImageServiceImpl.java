package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.entity.Image;

import com.developer.auctionapp.repository.ImageRepository;
import com.developer.auctionapp.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }
}
