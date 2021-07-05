package com.cognizant.springlearn2.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
	super(authenticationManager);
	// TODO Auto-generated constructor stub
    }

    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
	    throws IOException, ServletException {
	LOGGER.info("Start");

	String header = req.getHeader("Authorization");

	LOGGER.debug(header);

	if (header == null || !header.startsWith("Bearer ")) {

	    chain.doFilter(req, res);

	}

	UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

	SecurityContextHolder.getContext().setAuthentication(authentication);

	chain.doFilter(req, res);

	LOGGER.info("End");
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
	String token = request.getHeader("Authorization");
	if (token != null) {
	    // parse the token.
	    Jws<Claims> jws;
	    try {

		String substring = token.substring(7);
		System.out.println(substring);

		jws = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(substring);

		String user = jws.getBody().getSubject();
		LOGGER.debug(user);

		if (user != null) {
		    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
		}

	    } catch (JwtException ex) {
		ex.printStackTrace();
		return null;
	    }
	    return null;
	}
	return null;
    }

}