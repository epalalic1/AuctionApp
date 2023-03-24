package com.developer.auctionapp.controller;

import com.developer.auctionapp.entity.Address;
import com.developer.auctionapp.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

/**
 * <p>AddressController</p>
 *
 * The rest controller with REST API calls to manipulate with Address objects on a route "/auctionapp/address"
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     *  <p>A method that is triggered on a route "/auctionapp/address/getAddressOfCurrentUser"</p>
     * @return address object of currently logged-in user
     */

    @GetMapping("/getAddressOfCurrentUser")
    public ResponseEntity<Address> getAddressOfCurrentUser() {
        return addressService.findAddressOfCurrentUser();
    }
}
