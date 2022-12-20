package com.developer.auctionapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * <p>Class JWTGenerator</p>
 * We use it to generate token for user authentication
 */
@Component
public class JWTGenerator {

    private final  String JWT_SECRET;

    private final long JWT_EXPIRATION;

    public JWTGenerator(@Value("${secret.jwt}")String  jwt_secret, @Value("${expiration.jwt}")long  jwt_expiration){
        this.JWT_SECRET = jwt_secret;
        this.JWT_EXPIRATION = jwt_expiration;
    }

    /**
     * A method that generates token from email and has its own expiration date
     * @param authentication we receive when user log in
     * @return token that we sent to user when he/she logs in
     */

    public String generateToken(Authentication authentication) {
        String email = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRATION);
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
        return token;
    }

    /**
     * A method for getting email from JWT token
     * @param token is string we receive
     * @return email as string from token
     */

    public String getEmailFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }
}
