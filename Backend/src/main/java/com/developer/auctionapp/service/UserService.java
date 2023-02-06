package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.request.UpdateUser;
import com.developer.auctionapp.dto.request.UserLoginRequest;
import com.developer.auctionapp.dto.request.UserRegisterRequest;
import com.developer.auctionapp.dto.response.AuthResponse;
import com.developer.auctionapp.dto.response.Response;
import com.developer.auctionapp.dto.response.UserResponse;
import com.developer.auctionapp.entity.User;
import com.developer.auctionapp.exception.UserAlreadyExistException;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * An interface that contains all the methods that a UserServiceImpl service should have
 */

public interface UserService {

    ResponseEntity<List<UserResponse>> getAllUsers();

    User registerNewUserAccount(UserRegisterRequest userRegisterRequest) throws UserAlreadyExistException;

    AuthResponse loginUser(UserLoginRequest userLoginRequest);

    User getCurrentUser();

    User updateUser(UpdateUser updateUser);

    Response deactivateUser();
}
