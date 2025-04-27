package com.projectmanagement.cw_dm2_be.Controller;
/*
import com.projectmanagement.cw_dm2_be.Repository.UserRepository;
import com.projectmanagement.cw_dm2_be.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("{/Username}")
    public User getUserByUsername(@PathVariable String Username) {
        return userRepo.findByUsername(Username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }
} */

//package com.projectmanagement.cw_dm2_be.Controller;

import com.projectmanagement.cw_dm2_be.Model.User;
import com.projectmanagement.cw_dm2_be.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
