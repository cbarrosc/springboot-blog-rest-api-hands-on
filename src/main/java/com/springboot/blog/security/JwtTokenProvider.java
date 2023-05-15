package com.springboot.blog.security;

import com.springboot.blog.exception.BlogAPIException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-milliseconds}")
    private long jwtExpirationDate;

    //generate JWT token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = now.plusSeconds(jwtExpirationDate);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Timestamp.valueOf(now))
                .setExpiration(Timestamp.valueOf(expirationDate))
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
       return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //get username from JWT token
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //validate JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new BlogAPIException(HttpStatus.UNAUTHORIZED, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new BlogAPIException(HttpStatus.UNAUTHORIZED, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new BlogAPIException(HttpStatus.UNAUTHORIZED, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new BlogAPIException(HttpStatus.UNAUTHORIZED, "JWT claims string is empty");
        }

    }

}
