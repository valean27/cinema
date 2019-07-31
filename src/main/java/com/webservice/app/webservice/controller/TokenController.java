package com.webservice.app.webservice.controller;

import com.webservice.app.webservice.model.JwtUser;
import com.webservice.app.webservice.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private JwtGenerator jwtGenerator;

    @PostMapping
    public String generate(@RequestBody final JwtUser user){

        return jwtGenerator.generate(user);

    }

}
