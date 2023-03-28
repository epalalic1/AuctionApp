package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.entity.Address;
import com.developer.auctionapp.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AddressServiceImplTest {

    @Mock private AddressServiceImpl addressServiceImpl;

    @Test
    void findAddressOfCurrentUser() {
        Address address = new Address(1L,"countryAddress", "cityAddress", "postcodeAddress","streetNameAddress",4L);
        Mockito.when(addressServiceImpl.findAddressOfCurrentUser()).thenReturn(ResponseEntity.of(Optional.of(address)));
        Address result = addressServiceImpl.findAddressOfCurrentUser().getBody();
        assert result != null;
        Assertions.assertEquals("countryAddress", result.getCountry());
        Assertions.assertEquals("postcodeAddress",result.getPostcode());
    }
}