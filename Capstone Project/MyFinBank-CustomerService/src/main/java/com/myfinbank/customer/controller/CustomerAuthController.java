package com.myfinbank.customer.controller;

import com.myfinbank.customer.model.Customer;
import com.myfinbank.customer.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerAuthController {

    private final CustomerRepository customerRepository;

    public CustomerAuthController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customer/login")
    public String showLoginPage() {
        return "customerLogin";
    }

    @PostMapping("/customer/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session,
                               Model model) {

        Customer customer = customerRepository.findByUserName(username);

        if (customer == null || !customer.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid username or password");
            return "customerLogin";
        }

        if (!"ACTIVE".equalsIgnoreCase(customer.getStatus())) {
            model.addAttribute("error", "Account is not active");
            return "customerLogin";
        }

        session.setAttribute("loggedInCustomerId", customer.getId());

        //  redirect to /customer/dashboard/{id}
        return "redirect:/customer/dashboard/" + customer.getId();
    }

    @GetMapping("/customer/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/customer/login";
    }
}
