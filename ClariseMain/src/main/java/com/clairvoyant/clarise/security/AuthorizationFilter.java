package com.clairvoyant.clarise.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

	@Value("${app.googleApi}")
	private String googleApi;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		HttpServletRequest httpRequest = request;
		HttpServletResponse httpResponse = response;
		log.info("response authToken --> " +
			httpRequest.getHeader("Authorization"));

		String authToken = httpRequest.getHeader("Authorization");
		// TODO: sometime token is coming as null

		if (!StringUtils.hasLength(authToken)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.sendError(401, "Unauthorised");
			return;
		}
		String[] header = authToken.split(" ");
		String token = header[1];
		DecodedJWT jwt = JWT.decode(token);
		String idToken = jwt.getClaim("sub").asString();
		RestTemplate restTemplate = new RestTemplate();
		String apiUrl = new StringBuffer("https://oauth2.googleapis.com/tokeninfo").append("?id_token={idToken}")
				.toString();
		ResponseEntity<Map> restResponse = null;
		try {
			restResponse = restTemplate.getForEntity(apiUrl, Map.class, idToken);
		} catch (Exception e) {
			restResponse = new ResponseEntity<Map>(HttpStatus.BAD_REQUEST);
		}

		if (!restResponse.getStatusCode().toString().contains("200")) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
			response.sendError(401, "Unauthorised");
			return;
		}
		setContext(idToken);

		filterChain.doFilter(request, response);
	}

	private void setContext(String accessToken) {
		try {
			DecodedJWT jwt = JWT.decode(accessToken);
		} catch (JWTDecodeException exception) {

		}

	}
}