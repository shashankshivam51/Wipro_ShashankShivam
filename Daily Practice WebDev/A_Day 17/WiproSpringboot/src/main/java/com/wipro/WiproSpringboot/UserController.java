package com.wipro.WiproSpringboot;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Map<String, Object>> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public String addUser(@RequestBody User obj) {
        userService.addUser(obj);
        return "User added!";
    }

    @GetMapping("/{id}")
    public Map<String, Object> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }
}
