package com.clairvoyant.clarise.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader("Authorization");
        if (!StringUtils.hasLength(authToken)) {
            throw new AuthenticationServiceException("Authentication failed");
        }
        String[] header = authToken.split(" ");
        String idToken = header[1];
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = new StringBuffer("https://oauth2.googleapis.com/tokeninfo")
                .append("?id_token={idToken}").toString();
        ResponseEntity<Map> restResponse = null;

        try {
            restResponse = restTemplate.getForEntity(apiUrl, Map.class, idToken);
        } catch (Exception e) {
            restResponse= new ResponseEntity<Map>(HttpStatus.BAD_REQUEST);
        }

        if (!restResponse.getStatusCode().toString().contains("200")) {
            throw new AuthenticationServiceException("Authentication failed");
        }
        setContext(idToken);
        filterChain.doFilter(request, response);
    }


    private void setContext(String accessToken) {
        try {
            DecodedJWT jwt = JWT.decode(accessToken);
            System.out.println(jwt);
        } catch (JWTDecodeException exception){

        }

    }
}