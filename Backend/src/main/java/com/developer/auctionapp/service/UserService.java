package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.response.UserResponse;
import java.util.List;

/**
 * An interface that contains all the methods that a UserServiceImpl service should have
 */

public interface UserService {

    List<UserResponse> getAllUsers();
}
