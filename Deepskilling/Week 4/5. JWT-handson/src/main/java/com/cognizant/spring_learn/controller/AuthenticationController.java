package com.cognizant.spring_learn.controller;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("Start");
        String user = getUser(authHeader);
        LOGGER.debug("Extracted user: {}", user);
        String token = generateJwt(user);
        LOGGER.info("End");
        return Collections.singletonMap("token", token);
    }

    private String getUser(String authHeader) {
        LOGGER.info("Start");
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            String encodedCredentials = authHeader.substring(6);
            byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
            String credentials = new String(decodedBytes);
            int colonIndex = credentials.indexOf(":");
            if (colonIndex != -1) {
                String user = credentials.substring(0, colonIndex);
                LOGGER.debug("Decoded user: {}", user);
                LOGGER.info("End");
                return user;
            }
        }
        LOGGER.info("End");
        return null;
    }

    private String generateJwt(String user) {
        LOGGER.info("Start");
        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user);

        // Set the token issue time as current time
        builder.setIssuedAt(new Date());

        // Set the token expiry as 20 minutes from now
        builder.setExpiration(new Date((new Date()).getTime() + 1200000));
        builder.signWith(SignatureAlgorithm.HS256, "secretkey");

        String token = builder.compact();
        LOGGER.debug("Generated token: {}", token);
        LOGGER.info("End");
        return token;
    }
}
