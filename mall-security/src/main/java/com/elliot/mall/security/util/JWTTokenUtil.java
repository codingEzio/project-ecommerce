package com.elliot.mall.security.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTokenUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(JWTTokenUtil.class);
	private static final String CLAIM_KEY_USERNAME = "sub";
	private static final String CLAIM_KEY_CREATED = "created";

	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long expiration;
	@Value("${jwt.tokenHead}")
	private String tokenHead;

	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			LOGGER.info("JWT token verification failed: {}", token);
		}
		return claims;
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	public String getUserNameFromToken(String token) {
		String username;
		try {
			Claims claims = getClaimsFromToken(token);

			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		String username = getUserNameFromToken(token);

		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		Date expiredDate = getExpiredDateFromToken(token);

		return expiredDate.before(new Date());
	}

	private Date getExpiredDateFromToken(String token) {
		Claims claims = getClaimsFromToken(token);

		return claims.getExpiration();
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_CREATED, new Date());

		return generateToken(claims);
	}

	public String refreshHeadToken(String oldToken) {
		if (StrUtil.isEmpty(oldToken)) {
			return null;
		}

		String token = oldToken.substring(tokenHead.length());
		if (StrUtil.isEmpty(token)) {
			return null;
		}

		Claims claims = getClaimsFromToken(token);
		if (claims == null) {
			return null;
		}
		if (isTokenExpired(token)) {
			return null;
		}
		if (tokenRefreshJustBefore(token, 30 * 60)) {
			return token;
		} else {
			claims.put(CLAIM_KEY_CREATED, new Date());
			return generateToken(claims);
		}
	}

	private boolean tokenRefreshJustBefore(String token, int time) {
		Claims claims = getClaimsFromToken(token);

		Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
		Date refreshDate = new Date();

		if (refreshDate.after(created) && refreshDate.before(DateUtil.offsetSecond(created, time))) {
			return true;
		}
		return false;
	}
}
