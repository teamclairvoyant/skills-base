package com.clairvoyant.clarise.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils implements Serializable {

	private static final long serialVersionUID = 234234523523L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	 @Value("${jwt.secret}")
	 private String jwtSecret;

	/**
	 * generate token for user
	 * 
	 * @param userDetails
	 * @return
	 */

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	/**
	 * while creating the token -Define claims of the token, like Issuer,
	 * Expiration, Subject, and the ID Sign the JWT using the HS512 algorithm and
	 * secret key.
	 * 
	 * @param claims
	 * @param subject
	 * @return
	 */

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	/**
	 * validate token
	 * 
	 * @param token
	 * @param userDetails
	 * @return
	 */

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	/**
	 * retrieve username from jwt token
	 * 
	 * @param token
	 * @return
	 */

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	/**
	 * retrieve expiration date from jwt token
	 * 
	 * @param token
	 * @return
	 */

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	/**
	 * get claims token
	 * 
	 * @param <T>
	 * @param token
	 * @param claimsResolver
	 * @return
	 */
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * for retrieving any information from token we will need the secret key
	 * 
	 * @param token
	 * @return
	 */

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}

	/**
	 * check if the token has expired
	 * 
	 * @param token
	 * @return
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}


}
