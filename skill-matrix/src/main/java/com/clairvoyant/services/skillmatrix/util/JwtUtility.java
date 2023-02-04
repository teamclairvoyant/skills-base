package com.clairvoyant.services.skillmatrix.util;

import com.clairvoyant.services.skillmatrix.configuration.constants.CommonConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtility implements Serializable {

    private static final long serialVersionUID = 234234523523L;

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /**
     * while creating the token -Define claims of the token, like Issuer,
     * Expiration, Subject, and the ID Sign the JWT using the HS512 algorithm and
     * secret key.
     *
     * @param claims Claims
     * @param subject Subject details
     * @return Return jwt token
     */

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + CommonConstants.JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512, jwtSecretKey)
            .compact();
    }

    /**
     * validate token
     *
     * @param token Jwt Token
     * @param userDetails User details
     * @return True if token is valid.
     */

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * retrieve username from jwt token
     *
     * @param token Jwt Token
     * @return Return username from jwt token
     */

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * retrieve expiration date from jwt token
     *
     * @param token Jwt Token
     * @return Return expiration date from jwt token
     */

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * get claims token
     *
     * @param token Jwt Token
     * @param claimsResolver Claims Resolver
     * @return Claims
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * for retrieving any information from token we will need the secret key
     *
     * @param token Jwt Token
     * @return Return claims from jwt token
     */

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
    }

    /**
     * check if the token has expired
     *
     * @param token jwt token
     * @return Return true of token is expired
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
