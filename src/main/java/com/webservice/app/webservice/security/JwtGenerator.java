package com.webservice.app.webservice.security;

import com.webservice.app.webservice.model.JwtUser;
import com.webservice.app.webservice.repository.AdminRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {

    @Autowired
    AdminRepository adminRepository;

    public String generate(JwtUser user) {
        try{
            JwtUser jwtUser = adminRepository.findById(user.getId()).orElseThrow(RuntimeException::new);
            if(!user.getUsername().equals(jwtUser.getUsername())){
                throw new RuntimeException("Wrong username");
            }
            if(!user.getRole().equals(jwtUser.getRole())){
                throw new RuntimeException("User not an admin.");
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }

        Claims claims = Jwts.claims()
                .setSubject(user.getUsername());
        claims.put("userId", String.valueOf(user.getId()));
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims).signWith(SignatureAlgorithm.HS512, "secretulMariei")
                .setExpiration(new Date(System.currentTimeMillis() + (60000)))
                .compact();
    }
}
