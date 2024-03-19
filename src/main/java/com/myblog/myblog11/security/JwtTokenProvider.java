package com.myblog.myblog11.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

    @Component
    public class JwtTokenProvider {

        @Value("${jwt.secret}")// this will take the value form application.properties file
        private String jwtSecret;

        @Value("${jwt.expirationMs}")
        private long jwtExpirationInMs;

        public String generateToken(Authentication authentication) {
            String username =authentication.getName();
            Date currentDate = new Date();
            Date expiryDate = new Date(currentDate.getTime() + jwtExpirationInMs); //it will set the expiration time from the current time.

            String token= Jwts.builder()// another way of creating objects. Builder method will build a method of type String.
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)//HS512 will create encrypted token which ia also safeguarded by secret key
                    .compact();// this java class to be maintained by springIOC.
            return token;
        }

        public String getUsernameFromJWT(String token) {
            Claims claims= Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        }

        public boolean validateToken(String token, UserDetails userDetails) {
            final String username = getUsernameFromToken(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        }

        private boolean isTokenExpired(String token) {
            Date expiration = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.before(new Date());
        }
    }


