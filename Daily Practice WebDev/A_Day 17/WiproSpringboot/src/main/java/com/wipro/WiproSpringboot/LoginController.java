package com.wipro.WiproSpringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/login")
    public String checkUser() {
        String sql = "INSERT INTO users(name, password) VALUES(?, ?)";
        jdbcTemplate.update(sql, "javatpoint", "java@javatpoint.com");
        return "login success";
    }
}
