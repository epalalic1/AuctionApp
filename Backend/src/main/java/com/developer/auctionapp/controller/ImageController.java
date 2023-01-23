package com.developer.auctionapp.controller;

import com.developer.auctionapp.entity.Image;
import com.developer.auctionapp.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p>Image controller</p>
 *
 * The rest controller with REST API calls to manipulate with Image objects on a route "/auctionapp/image"
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/image/getAll"</p>
     * @return all images from the database
     */

    @GetMapping("/getAll")
    public ResponseEntity<List<Image>> getAll() {
        return imageService.getAll();
    }
}
