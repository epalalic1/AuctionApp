package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.entity.Address;
import com.developer.auctionapp.entity.User;
import com.developer.auctionapp.repository.AddressRepository;
import com.developer.auctionapp.service.AddressService;
import com.developer.auctionapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * <p>Class that implements AddressService interface and we use it to comunicate with the database</p>
 */

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final UserService userService;

    private final AddressRepository addressRepository;

    public AddressServiceImpl(final UserService userService, final AddressRepository addressRepository) {
        this.userService = userService;
        this.addressRepository = addressRepository;
    }

    /**
     *  The method used to get address object of currently logged-in user
     * @return address object
     */

    @Override
    public ResponseEntity<Address> findAddressOfCurrentUser() {
        User currentUser = userService.getCurrentUser();
        List<Address> list = addressRepository.findAll();
        if (currentUser.getAddress() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.of(addressRepository.findById(currentUser.getAddress().getId()));
    }
}
