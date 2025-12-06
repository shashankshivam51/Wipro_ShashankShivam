package com.myfinbank.customer.api;

import com.myfinbank.customer.model.Customer;
import com.myfinbank.customer.repository.CustomerRepository;
import com.myfinbank.customer.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    private final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;

    public ApiAuthController(CustomerRepository customerRepository, JwtUtil jwtUtil) {
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Username and password are required"));
        }

        Customer customer = customerRepository.findByUserName(username);

        if (customer == null || !password.equals(customer.getPassword())) {
            return ResponseEntity.status(401)
                    .body(Map.of("error", "Invalid username or password"));
        }

        if (!"ACTIVE".equalsIgnoreCase(customer.getStatus())) {
            return ResponseEntity.status(403)
                    .body(Map.of("error", "Account is not active"));
        }

        String token = jwtUtil.generateToken(customer.getId(), customer.getUserName());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "customerId", customer.getId(),
                "username", customer.getUserName()
        ));
    }
}
