package com.developer.auctionapp.controller;


import com.developer.auctionapp.service.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auctionapp")
public class InitializeController {
    private final InitializeService initializeService;

    public InitializeController(InitializeService initializeService) {
        this.initializeService = initializeService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/")
    public int initialize(){
        initializeService.initializeCategoryTable();
        initializeService.initializeSubcategoryTable();
        initializeService.initializeUserTable();
        initializeService.initializeProductTable();
        initializeService.initializeImageTable();
        initializeService.initializeBidTable();
        return 1;
    }
}