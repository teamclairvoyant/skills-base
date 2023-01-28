package com.clairvoyant.clarise.security;

import com.clairvoyant.clarise.configuration.constants.CommonConstants;
import com.clairvoyant.clarise.service.impl.UserDetailsServiceDefault;
import com.clairvoyant.clarise.util.JWTUtility;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private UserDetailsServiceDefault userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);


	 @Override
	    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
	        String authorization = httpServletRequest.getHeader(CommonConstants.HEADER_STRING);
	        String token = null;
	        String userName = null;

	        if(null != authorization && authorization.startsWith(CommonConstants.TOKEN_PREFIX)) {
	            token = authorization.replace("Bearer","").trim();
	            userName = jwtUtility.getUsernameFromToken(token);
	            
	        }

	        if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails
	                    = userDetailsService.loadUserByUsername(userName);

	            if(jwtUtility.validateToken(token,userDetails)) {
	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
	                        = new UsernamePasswordAuthenticationToken(userDetails,
	                        null, userDetails.getAuthorities());

	                usernamePasswordAuthenticationToken.setDetails(
	                        new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
	                );

	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }

	        }
	        filterChain.doFilter(httpServletRequest, httpServletResponse);
	    }
}