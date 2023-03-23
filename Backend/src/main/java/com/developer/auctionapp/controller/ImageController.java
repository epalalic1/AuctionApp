package com.developer.auctionapp.controller;

import com.developer.auctionapp.entity.Image;
import com.developer.auctionapp.service.ImageService;
import com.developer.auctionapp.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private final NotificationService notificationService;

    public ImageController(ImageService imageService, NotificationService notificationService) {
        this.imageService = imageService;
        this.notificationService = notificationService;
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
