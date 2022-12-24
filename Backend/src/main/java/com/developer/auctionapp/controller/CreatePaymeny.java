package com.developer.auctionapp.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp/createPayment")
public class CreatePaymeny {

    private final  String sk_secret;

    public CreatePaymeny(@Value("${stripe.sk_key}")String  sk_secret) {
        this.sk_secret = sk_secret;
    }


    @PostMapping("/")
    public void createPaymentIntent() throws StripeException {
        Stripe.apiKey = "sk_test_51MHp6LAHTI83CHaUAVffrtD6lM1KKDUyd5rTLeqdEnVXpGqdL6ebBkNHh41orQUEc30GHVcS9XTQrgtf9v7JNweM00rzz20Xuc";

        CustomerCreateParams params =
                CustomerCreateParams.builder()
                        .build();

        Customer customer = Customer.create(params);

        PaymentIntentCreateParams params1 =
                PaymentIntentCreateParams
                        .builder()
                        .setCustomer(customer.getId())
                        .setSetupFutureUsage(PaymentIntentCreateParams.SetupFutureUsage.OFF_SESSION)
                        .setAmount(1099L)
                        .setCurrency("eur")
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params1);
        paymentIntent.confirm((Map<String, Object>) params1);
        System.out.println(paymentIntent);
    }

}
