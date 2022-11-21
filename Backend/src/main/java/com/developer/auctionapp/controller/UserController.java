package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.response.UserResponseDto;
import com.developer.auctionapp.entity.User;
import com.developer.auctionapp.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auctionapp/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAll")
    public List<UserResponseDto> findAllUsers() {
        return userService.getAllUsers();
    }
}
