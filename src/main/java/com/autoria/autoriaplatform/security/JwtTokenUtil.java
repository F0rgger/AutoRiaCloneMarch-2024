package com.autoria.autoriaplatform.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        JwtParser jwtParser = Jwts.parser()
                .setSigningKey(jwtSecret)
                .build(); // Build the JwtParser using the parserBuilder API
        Claims claims = jwtParser.parseClaimsJws(token).getBody(); // Parse the token and extract claims
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JwtParser jwtParser = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .build(); // Build the JwtParser using the parserBuilder API
            jwtParser.parseClaimsJws(token); // Parse the token
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
