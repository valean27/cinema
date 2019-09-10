package com.webservice.app.webservice.controller;

import com.webservice.app.webservice.dao.User;
import com.webservice.app.webservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("/all")
    public List<User> getAll() {
        return repository.findAll();
    }

    @PutMapping("/insert")
    public User insert(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("age") int age){
        User userToInsert = new User(firstName, lastName, age);
        repository.save(userToInsert);
        return userToInsert;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id){
        User userToDelete = repository.findById(id).orElseThrow(RuntimeException::new);
        repository.delete(userToDelete);
        return userToDelete + "\nHas been deleted!!";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") int id, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("age") int age){
        User userToUpdate = repository.findById(id).orElseThrow(RuntimeException::new);
        if (firstName != null) {
            userToUpdate.setFirstName(firstName);
        }
        if(lastName != null){
            userToUpdate.setLastName(lastName);
        }
        if(age > 0){
            userToUpdate.setAge(age);
        }
        userToUpdate.setUserId(id);
        repository.save(userToUpdate);
        return userToUpdate + "\n User with id" +" " + id + " has been updated with the above values.";
    }


}
