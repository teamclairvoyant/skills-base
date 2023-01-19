package com.clairvoyant.clarise.security;

import com.clairvoyant.clarise.service.impl.CustomUserDetailsService;
import com.clairvoyant.clarise.util.JwtUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtility;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

	@Value("${app.googleApi}")
	private String googleApi;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		String userName = null;
		String token = null;
		logger.info("response authToken --> " + httpServletRequest.getHeader("Authorization"));
		String authorization = httpServletRequest.getHeader("Authorization");
		if (!StringUtils.hasLength(authorization)) {
			httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			httpServletResponse.sendError(401, "Unauthorised");
			return;
		}

		String[] header = authorization.split(" ");
		token = header[1];
		DecodedJWT jwt = JWT.decode(token);
		String idToken = jwt.getClaim("sub").asString();
		RestTemplate restTemplate = new RestTemplate();
		String apiUrl = new StringBuffer("https://oauth2.googleapis.com/tokeninfo").append("?id_token={idToken}")
				.toString();
		ResponseEntity<Map> restResponse = null;

		if (null != authorization && authorization.startsWith("Bearer ")) {
			token = authorization.substring(7);
			userName = jwtUtility.getUsernameFromToken(token);
		}

		try {
			restResponse = restTemplate.getForEntity(apiUrl, Map.class, idToken);
		} catch (Exception e) {
			restResponse = new ResponseEntity<Map>(HttpStatus.BAD_REQUEST);
		}
		if (!restResponse.getStatusCode().toString().contains("200")) {
			httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
			httpServletResponse.sendError(401, "Unauthorised");
			return;
		}

		if (null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

			if (jwtUtility.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}

		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
