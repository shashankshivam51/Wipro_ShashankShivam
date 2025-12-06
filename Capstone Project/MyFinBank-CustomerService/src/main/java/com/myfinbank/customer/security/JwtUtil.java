package com.myfinbank.customer.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 1 day expiry
    private static final long EXPIRATION_MS = 24 * 60 * 60 * 1000;

    // For demo: hard-coded key. In production, move to config and keep it secret.
    // Must be at least 32 bytes for HS256.
    private final Key key = Keys.hmacShaKeyFor(
            "MYFINBANK_SUPER_SECRET_KEY_32BYTES__".getBytes(StandardCharsets.UTF_8)
    );

    public String generateToken(Long customerId, String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(String.valueOf(customerId))
                .claim("username", username)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    public Long getCustomerId(String token) {
        return Long.valueOf(validateToken(token).getBody().getSubject());
    }

    public String getUsername(String token) {
        return validateToken(token).getBody().get("username", String.class);
    }
}
