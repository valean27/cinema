package com.webservice.app.webservice.security;

import com.webservice.app.webservice.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {
    public String generate(JwtUser user) {

        Claims claims = Jwts.claims()
                .setSubject(user.getUsername());
        claims.put("userId", String.valueOf(user.getId()));
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims).signWith(SignatureAlgorithm.HS512, "secretulMariei")
                .compact();
    }
}
