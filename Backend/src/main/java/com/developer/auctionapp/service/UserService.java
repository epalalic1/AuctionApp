package com.developer.auctionapp.service;



import com.developer.auctionapp.dto.response.UserResponseDto;
import com.developer.auctionapp.entity.User;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();
}
