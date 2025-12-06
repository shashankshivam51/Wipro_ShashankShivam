package com.myfinbank.customer.api;

import com.myfinbank.customer.model.Customer;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestSecureApiController {

    @GetMapping("/api/me")
    public Map<String, Object> me(Authentication authentication) {
        Customer customer = (Customer) authentication.getPrincipal();

        return Map.of(
                "id", customer.getId(),
                "username", customer.getUserName(),
                "email", customer.getEmail()
        );
    }
}
