package com.developer.auctionapp.service;

import com.developer.auctionapp.entity.Image;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * An interface that contains all the methods that a ImageServiceImpl service should have
 */

public interface ImageService {

    ResponseEntity<List<Image>> getAll();
}
