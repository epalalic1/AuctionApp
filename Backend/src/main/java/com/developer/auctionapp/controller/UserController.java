package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.UserLoginRequest;
import com.developer.auctionapp.dto.request.UserRegisterRequest;
import com.developer.auctionapp.dto.response.AuthResponse;
import com.developer.auctionapp.dto.response.UserResponse;
import com.developer.auctionapp.entity.User;
import com.developer.auctionapp.exception.UserAlreadyExistException;
import com.developer.auctionapp.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>User controller</p>
 *
 * The rest controller with REST API calls to manipulate with User objects on a route "/auctionapp/user"
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/user/getAll"</p>
     * @return all users from the database
     */

    @GetMapping("/getAll")
    public List<UserResponse> findAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/user/register"</p>
     * @param userRegisterRequest is DTO that contains all data we need to register user
     * @return User object that represent registered
     * @throws UserAlreadyExistException if the email of the user we want to register alredy exists
     */

    @PostMapping("/register")
    public User registerUser(@RequestBody UserRegisterRequest userRegisterRequest) throws UserAlreadyExistException {
        return userService.registerNewUserAccount(userRegisterRequest);
    }

    /**
     *  <p>A method that is triggered on a route "/auctionapp/user/login"</p>
     * @param userLoginRequest is DTO that contains email and password of the user we want to login
     * @return AuthResponse that contains all information for logged-in user
     */

    @PostMapping("/login")
    public AuthResponse loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        return userService.loginUser(userLoginRequest);
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/user/getCurrentUser"</p>
     * @return current logged-in user
     */

    @GetMapping("/getCurrentUser")
    public User findCurrentUser (){
        return userService.getCurrentUser();
    }
}
