package com.company.propertymanagement.util;

import com.company.propertymanagement.service.implementation.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.MalformedKeyException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@Log4j2
public class JWTUtil {

    @Value("${app.JWTSecret}")
    private String jwtSecret;
    @Value("${app.JWTExpirationMs}")
    private int jwtExpirationMs;

    // public String generateJWTToken(Authentication authentication) {
    //    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    //    return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
    //            .setExpiration(new Date((new Date().getTime() + jwtExpirationMs))).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    // }

    public String generateJWTToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Instant now = Instant.now();
        Instant expiry = now.plus(jwtExpirationMs, ChronoUnit.MILLIS);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiry))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJWToken(String token) {
        String username = null;
        try {
            username = Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (Exception e) {
            log.error("An error occurred.");
        }
        return username;
    }

    public boolean validateJwtToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(token);
            // If the parsing succeeds, the token is valid
            return true;
        } catch (SignatureException e) {
            // If an exception occurs during parsing, the token is invalid
            log.error("Invalid JWT signature: {}", e.getMessage());
            return false;
        } catch (MalformedKeyException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token not supported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}

