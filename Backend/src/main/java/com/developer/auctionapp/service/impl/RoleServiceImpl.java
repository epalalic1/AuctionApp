package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.entity.Role;
import com.developer.auctionapp.repository.RoleRepository;
import com.developer.auctionapp.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * <p>Class that implements RoleService interface and we use it to comunicate with the database</p>
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * The method used to get all roles from database and transform them into Data Transform Objects
     * @return list of Data Transform Objects which each of them represent one Role
     */


    @Override
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> list = roleRepository.findAll();
        if (list.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.of(Optional.of(list));
    }
}
