package com.developer.auctionapp.service;

import com.developer.auctionapp.entity.Address;
import java.util.Optional;

/**
 * An interface that contains all the methods that aAddressServiceImpl service should have
 */

public interface AddressService {

    Optional<Address> findAddressOfCurrentUser();
}
