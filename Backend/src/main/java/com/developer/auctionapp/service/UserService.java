package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.response.UserResponse;
import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();
}
