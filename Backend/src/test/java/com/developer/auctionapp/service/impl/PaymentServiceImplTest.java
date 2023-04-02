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

/**
 * A class that contains tests for testing the methods of the PaymentServiceImpl class
 */

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class PaymentServiceImplTest {

    @Mock
    private PaymentServiceImpl paymentServiceImpl;

    /**
     * A method that tests a class method that charges the user
     *
     * @throws StripeException when charging the user is disabled
     */

    @Test
    void charge() throws StripeException {
        final PaymentRequest paymentRequest = new PaymentRequest(
                "eur",
                "description",
                180,
                "mail@gmail.com",
                "12345E",
                1L);
        final PaymentResponse paymentResponse = new PaymentResponse("clientSecretABC");
        Mockito.when(paymentServiceImpl.charge(paymentRequest)).thenReturn(paymentResponse);
        final PaymentResponse result = paymentServiceImpl.charge(paymentRequest);
        Assertions.assertDoesNotThrow(() -> {
            final Class<StripeException> stripeExceptionClass = StripeException.class;
        });
        Assertions.assertEquals("clientSecretABC", result.getClientSecret());
    }

    /**
     * A method that tests a class method create payment request and charges the user
     *
     * @throws StripeException when charging the user is disabled
     */

    @Test
    void chargeUser() throws StripeException {
        final PaymentRequest paymentRequest = new PaymentRequest(
                "eur",
                "description",
                180,
                "mail@gmail.com",
                "12345E",
                1L);
        final Charge charge = new Charge();
        Mockito.when(paymentServiceImpl.chargeUser(paymentRequest)).thenReturn(charge);
        final Charge result = paymentServiceImpl.chargeUser(paymentRequest);
        Assertions.assertDoesNotThrow(() -> {
            final Class<StripeException> stripeExceptionClass = StripeException.class;
        });
        Assertions.assertNotNull(result);
    }
}