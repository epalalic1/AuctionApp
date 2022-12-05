package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.BidRequestDto;
import com.developer.auctionapp.dto.response.BidResponse;
import com.developer.auctionapp.entity.Bid;
import com.developer.auctionapp.service.BidService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp/bid")
public class BidController {

    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @GetMapping("/getAll")
    public List<BidResponse> getAll() {
        return bidService.getAll();
    }

    @PostMapping("/addBid")
    public Bid addBid(@RequestBody BidRequestDto bidRequestDto){
        return bidService.addBid(bidRequestDto);
    }
}
