package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.PaymentRequest;
import com.developer.auctionapp.dto.response.PaymentResponse;
import com.developer.auctionapp.service.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp/createPayment")
public class CreatePayment {

    private final PaymentService paymentService;

    public CreatePayment(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest) throws StripeException {
       return paymentService.charge(paymentRequest);
    }
}
