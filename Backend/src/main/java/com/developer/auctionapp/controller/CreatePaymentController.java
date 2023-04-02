package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.PaymentRequest;
import com.developer.auctionapp.dto.response.PaymentResponse;
import com.developer.auctionapp.service.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.*;

/**
 * <p>CreatePaymentController</p>
 *
 * The rest controller with REST API call to finish the payment on a route "/auctionapp/createPayment"
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp/createPayment")
public class CreatePaymentController {

    private final PaymentService paymentService;

    public CreatePaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    /**
     *  <p>A method that is triggered on a route "/auctionapp/createPayment/"</p>
     * @param paymentRequest DTO object that contains all data we need for payment
     * @return DTO object containing information whether it was successful payment
     * @throws StripeException in case that we had problems with paying in Stripe
     */

    @PostMapping("/")
    public PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest) throws StripeException {
        return paymentService.charge(paymentRequest);
    }
}
