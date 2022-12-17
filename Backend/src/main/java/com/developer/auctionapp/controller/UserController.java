package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.response.UserResponse;
import com.developer.auctionapp.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
