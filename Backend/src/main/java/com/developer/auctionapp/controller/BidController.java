package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.BidRequestDto;
import com.developer.auctionapp.dto.response.BidResponse;
import com.developer.auctionapp.entity.Bid;
import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.service.BidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>Bid controller</p>
 *
 * The rest controller with REST API calls to manipulate with Bid objects on a route "/auctionapp/bid"
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp/bid")
public class BidController {

    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/bids/getAll"</p>
     * @return all bids from the database
     */

    @GetMapping("/getAll")
    public ResponseEntity<List<BidResponse>> getAll() {
        return bidService.getAll();
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/bids/addBid" and we use it to
     * insert Bid object into database with POST request</p>
     * @param bidRequestDto object that we are receiving which have to be transformed into Bid object
     * @return Bid object that is inserted
     */

    @PostMapping("/addBid")
    public ResponseEntity<Bid> addBid(@RequestBody BidRequestDto bidRequestDto) {
        return bidService.addBid(bidRequestDto);
    }
}
