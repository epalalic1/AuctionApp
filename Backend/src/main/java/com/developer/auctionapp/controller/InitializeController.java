package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.response.Response;
import com.developer.auctionapp.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>Initialize controller</p>
 *
 * The rest controller with REST API call to seed the database with the initial data
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp")
public class InitializeController {

    private final InitializeService initializeService;

    public InitializeController(InitializeService initializeService) {
        this.initializeService = initializeService;
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/"</p>
     *
     * Returns one of the following status codes inside Response object:
     * 200: database is successfully seeded with the initital data
     * 400: error occured while seeding one of the tables
     *
     * @return Response object with specific status code and a message
     */

    @GetMapping("/")
    public ResponseEntity initialize() {
        return initializeService.initializeDatabase();
    }
}
