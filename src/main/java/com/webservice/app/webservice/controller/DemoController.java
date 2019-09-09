package com.webservice.app.webservice.controller;

import com.webservice.app.webservice.dao.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest/")
@Api(value = "Demo User Controller Resource", description = "Shows the user information")
public class DemoController {

    @ApiOperation(value = "Returns all Users")
    @ApiResponses(value = {
            @ApiResponse(code = 100, message = "100 is the response"),
            @ApiResponse(code = 200, message = "Successful response")
    })
    @GetMapping
    public List<User> getUsers(){
        return Arrays.asList(
                new User("Stefan", "Valean", 23),
                new User("Andrei", "Moldovan", 35)
        );
    }

    @ApiOperation(value = "Returns a single new User")
    @GetMapping("/{firstName}")
    public User getUser(@PathVariable("firstName") final String firstName){
        return new User(firstName, "RandomStringlastName", 20);
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }


}
