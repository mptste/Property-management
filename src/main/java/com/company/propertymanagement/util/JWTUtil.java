package com.company.propertymanagement.util;

import com.company.propertymanagement.service.implementation.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
public class JWTUtil {

    @Value("${app.JWTSecret}")
    private String JWTSecret;
    @Value("${app.JWTExpirationMs}")
    private int JWTExpirationMs;

    public String generateJWTToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date((new Date().getTime() + JWTExpirationMs))).signWith(SignatureAlgorithm.HS512, JWTSecret).compact();
    }

    public String getUsernameFromJWToken(String token) {

        return token;
    }

}

