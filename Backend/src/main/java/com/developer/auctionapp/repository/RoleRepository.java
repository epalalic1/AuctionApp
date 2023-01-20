package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * A method that returns a role based on name
     * @param name by which we are looking for the role
     * @return role with specific name
     */

    @Query
    Role findByName(String name);
}
