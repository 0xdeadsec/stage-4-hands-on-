package com.cognizant.springlearn2.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
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

    private String secretKey = "secretKey"; // can override from properties file

    private final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
	LOGGER.debug("START");
	LOGGER.debug("Calling AuthenicationController.authenticate() with method 'GET'");
	Map<String, String> map = new HashMap<>();
	map.put("token", generateJwt(getUser(authHeader)));
	LOGGER.info("END");
	return map;
    }

    private String generateJwt(String user) {
	LOGGER.debug("Calling AuthenicationController.generateJwt()");
	JwtBuilder builder = Jwts.builder();
	builder.setSubject(user);
	Date date = new Date();
	builder.setIssuedAt(date);
	builder.setExpiration(new Date(date.getTime() + 1200000));
	builder.signWith(SignatureAlgorithm.HS256, "secretKey");
	String token = builder.compact();
	LOGGER.debug("Returning from AuthenicationController.generateJwt()");
	return token;
    }

    private String getUser(String authHeader) {
	LOGGER.debug("Starting AuthenicationController.getUser()");
	byte[] decode = Base64.getDecoder().decode(authHeader.substring(6));
	String x = new String(decode);
	LOGGER.debug("Returning from AuthenicationController.getUser()");
	return x;
    }
}
