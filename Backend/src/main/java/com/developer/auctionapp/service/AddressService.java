package com.developer.auctionapp.service;

import com.developer.auctionapp.entity.Address;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * An interface that contains all the methods that aAddressServiceImpl service should have
 */

public interface AddressService {

    ResponseEntity<Address> findAddressOfCurrentUser();
}
