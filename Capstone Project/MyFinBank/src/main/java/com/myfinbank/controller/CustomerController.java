package com.myfinbank.controller;

import com.myfinbank.model.Customer;
import com.myfinbank.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    


    @GetMapping("/customer/add")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "addCustomer";
    }

    @PostMapping("/customer/add")
    public String addCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        customer.setStatus("INACTIVE");
        customer.setAccountNo(null);
        customerRepository.save(customer);

        model.addAttribute("message", "Customer created with INACTIVE status. Wait for admin approval.");
        model.addAttribute("customer", new Customer());
        return "addCustomer";
    }
}
