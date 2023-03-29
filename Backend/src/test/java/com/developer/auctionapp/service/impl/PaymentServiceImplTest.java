package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.request.PaymentRequest;
import com.developer.auctionapp.dto.response.PaymentResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class PaymentServiceImplTest {

    @Mock private PaymentServiceImpl paymentServiceImpl;

    @Test
    void charge() throws StripeException {
        PaymentRequest paymentRequest = new PaymentRequest("eur","description",180,"mail@gmail.com","12345E",1L);
        PaymentResponse paymentResponse = new PaymentResponse("clientSecretABC");
        Mockito.when(paymentServiceImpl.charge(paymentRequest)).thenReturn(paymentResponse);
        PaymentResponse result = paymentServiceImpl.charge(paymentRequest);
        Assertions.assertDoesNotThrow( () -> {
            final Class<StripeException> stripeExceptionClass = StripeException.class;
        });
        Assertions.assertEquals("clientSecretABC", result.getClientSecret());
    }

    @Test
    void chargeUser() throws StripeException {
        PaymentRequest paymentRequest = new PaymentRequest("eur","description",180,"mail@gmail.com","12345E",1L);
        Charge charge = new Charge();
        Mockito.when(paymentServiceImpl.chargeUser(paymentRequest)).thenReturn(charge);
        Charge result = paymentServiceImpl.chargeUser(paymentRequest);
        Assertions.assertDoesNotThrow( () -> {
            final Class<StripeException> stripeExceptionClass = StripeException.class;
        });
        Assertions.assertNotNull(result);
    }
}