package com.webservice.app.webservice.security;

import com.webservice.app.webservice.model.JwtUser;
import com.webservice.app.webservice.repository.AdminRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    @Autowired
    AdminRepository adminRepository;

    private String secret = "secretulMariei";

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = adminRepository.findById(Integer.parseInt((String) body.get("userId"))).orElseThrow(RuntimeException::new);
        }catch(Exception e){
            throw new RuntimeException("Token invalid or expired");
        }
        return jwtUser;
    }
}
