package com.developer.auctionapp.controller;

import com.developer.auctionapp.entity.Role;
import com.developer.auctionapp.service.RoleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auctionapp/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAll")
    public List<Role> findAllUsers() {
        return roleService.getAllRoles();
    }

}
