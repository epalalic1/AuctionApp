package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.response.BidResponseDto;
import com.developer.auctionapp.entity.Bid;
import com.developer.auctionapp.service.BidService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctionapp/bid")
public class BidController {

    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAll")
    public List<BidResponseDto> getAll(){
        return bidService.getAll();}



}

