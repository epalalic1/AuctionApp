package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.entity.Image;
import com.developer.auctionapp.repository.ImageRepository;
import com.developer.auctionapp.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * <p>Class that implements ImageService interface and we use it to comunicate with the database</p>
 */

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(final ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * The method used to get all categories from database
     * @return list of images
     */

    @Override
    public ResponseEntity<List<Image>> getAll() {
        List<Image> lista = imageRepository.findAll();
        if (lista.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.of(Optional.of(lista));
    }
}
