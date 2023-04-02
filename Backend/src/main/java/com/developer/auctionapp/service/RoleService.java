package com.developer.auctionapp.service;

import com.developer.auctionapp.entity.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * An interface that contains all the methods that a RoleServiceImpl service should have
 */

public interface RoleService {

    ResponseEntity<List<Role>> getAllRoles();
}
