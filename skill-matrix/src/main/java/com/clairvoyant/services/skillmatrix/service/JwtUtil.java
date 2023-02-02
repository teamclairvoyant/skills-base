package com.clairvoyant.services.skillmatrix.service;

import io.jsonwebtoken.Jwts;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public String generateToken(String token, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",role);
        return createToken(claims, token);
    }

    private String createToken(Map<String, Object> claims, String token) {
        return Jwts.builder().setClaims(claims).setSubject(token).compact();
    }

}
