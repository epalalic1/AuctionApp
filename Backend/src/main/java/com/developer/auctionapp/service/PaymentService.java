package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.request.PaymentRequest;
import com.developer.auctionapp.dto.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {

    PaymentResponse charge(PaymentRequest paymentRequest) throws StripeException;
}
