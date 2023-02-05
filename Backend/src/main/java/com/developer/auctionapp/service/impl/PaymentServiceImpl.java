package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.request.PaymentRequest;
import com.developer.auctionapp.dto.response.PaymentResponse;
import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.repository.ProductRepository;
import com.developer.auctionapp.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;


/**
 *  <p>Class that implements PaymentService interface, and  we use it to finish the payment in Stripe
 */

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final  String sk_secret;

    private final ProductRepository productRepository;

    public PaymentServiceImpl (final @Value("${stripe.sk_key}")String  sk_secret, final ProductRepository productRepository) {
        this.sk_secret = sk_secret;
        this.productRepository = productRepository;
    }

    /**
     * The method we use to create Charge in Stripe and update the product in database
     * @param paymentRequest DTO we receive when user wants to do payment
     * @return PaymentResponse
     * @throws StripeException in case we had problems while paying
     */

    @Override
    public PaymentResponse charge(final PaymentRequest paymentRequest) throws StripeException {
        Stripe.apiKey = sk_secret;
        chargeUser(paymentRequest);
        Product  product = productRepository.findById(paymentRequest.getProductId()).get();
        product.setStatus(true);
        productRepository.save(product);
        return new PaymentResponse("Payment is successeded");
    }

    /**
     * The method we use to create Charge in Stripe
     * @param paymentRequest DTO we receive when user wants to do payment
     * @return object Charge
     * @throws StripeException in case we had problems while paying
     */

    public Charge chargeUser(final PaymentRequest paymentRequest)
            throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", paymentRequest.getAmount()*100);
        chargeParams.put("currency", "usd");
        chargeParams.put("description", paymentRequest.getDescription());
        chargeParams.put("source", paymentRequest.getStripeToken());
        return Charge.create(chargeParams);
    }
}
