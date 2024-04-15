package com.company.propertymanagement.util;

import com.company.propertymanagement.service.implementation.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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



        return token;
    }

}

