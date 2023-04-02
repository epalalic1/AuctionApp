package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.entity.Address;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;

/**
 * A class that contains tests for testing the methods of the AddressServiceImpl class
 */

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AddressServiceImplTest {

    @Mock
    private AddressServiceImpl addressServiceImpl;

    /**
     * A method that tests a class method that returns address of the currently logged-in user
     */

    @Test
    void findAddressOfCurrentUser() {
        final Address address = new Address(
                1L,
                "countryAddress",
                "cityAddress",
                "postcodeAddress",
                "streetNameAddress",
                4L);
        Mockito.when(addressServiceImpl.findAddressOfCurrentUser()).thenReturn(ResponseEntity.of(Optional.of(address)));
        final Address result = addressServiceImpl.findAddressOfCurrentUser().getBody();
        assert result != null;
        Assertions.assertEquals("countryAddress", result.getCountry());
        Assertions.assertEquals("postcodeAddress", result.getPostcode());
    }
}