package com.mpp.backend.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import static com.mpp.backend.Security.Utils.Constants.JWT_EXPIRATION_TIME;
import static com.mpp.backend.Security.Utils.Constants.JWT_SECRET;

@Component
public class JWTGenerator {
    public static String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentTime = new Date();
        Date expirationTime = new Date(currentTime.getTime() + JWT_EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentTime)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();

        return token;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("JWT token has expired or is invalid");
        }
    }
}
