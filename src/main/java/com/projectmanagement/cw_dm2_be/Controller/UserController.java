package com.projectmanagement.cw_dm2_be.Controller;

import com.projectmanagement.cw_dm2_be.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String email,
                          @RequestParam String role) {
        userService.addUser(username, password, email, role);
        return "User added";
    }

    @PutMapping("/update")
    public String updateUser(@RequestParam int id,
                             @RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email,
                             @RequestParam String role) {
        userService.updateUser(id, username, password, email, role);
        return "User updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "User deleted";
    }

    @GetMapping("/all")
    public List<Object[]> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Object[] getUserById(@PathVariable int id) {

        return userService.getUserById(id);
    }
}
