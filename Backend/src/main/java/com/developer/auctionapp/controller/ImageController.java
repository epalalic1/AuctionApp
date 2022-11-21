package com.developer.auctionapp.controller;

import com.developer.auctionapp.entity.Image;
import com.developer.auctionapp.service.ImageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auctionapp/image")
public class ImageController {

    private final ImageService imageService;


    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAll")
    public List<Image> getAll(){
        return imageService.getAll();}
}
