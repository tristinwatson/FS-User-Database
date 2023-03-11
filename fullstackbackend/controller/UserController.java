package com.fullstackapp.fullstackbackend.controller;

import com.fullstackapp.fullstackbackend.exception.UserNotFoundException;
import com.fullstackapp.fullstackbackend.model.User;
import com.fullstackapp.fullstackbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// controller for get/post/put/delete
@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepo UserRepo;

    // creates new user and generates a unique id
    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return UserRepo.save(newUser);
    }

    // gets all existing users
    @GetMapping("/users")
    List<User> getAllUser() {
        return UserRepo.findAll();
    }

    // gets an existing user by id
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        return UserRepo.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    // edits an existing user in repo by id
    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return UserRepo.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return UserRepo.save(user);
                }).orElseThrow(()-> new UserNotFoundException(id));
    }

    // deletes an existing user in repo by id
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {
        if(!UserRepo.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        UserRepo.deleteById(id);
        return "User with id " + id + " has been deleted";
    }
}
