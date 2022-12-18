package com.developer.auctionapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Class SecurityConstants contains all constants that we are going to use in project
 */

@Component
public class SecurityConstants {

    public static String JWT_SECRET;

    @Autowired
    public SecurityConstants (@Value("${secret.jwt}")String  JWT_SECRET) {
        this.JWT_SECRET = JWT_SECRET;
    }

    public static final long JWT_EXPIRATION = 70000;

}
