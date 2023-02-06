package com.developer.auctionapp.controller;

import com.developer.auctionapp.entity.Role;
import com.developer.auctionapp.service.RoleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p>Role controller</p>
 *
 * The rest controller with REST API calls to manipulate with Role objects on a route "/auctionapp/role"
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/role/getAll"</p>
     * @return all roles from the database
     */

    @GetMapping("/getAll")
    public List<Role> findAllRoles() {
        return roleService.getAllRoles();
    }
}
