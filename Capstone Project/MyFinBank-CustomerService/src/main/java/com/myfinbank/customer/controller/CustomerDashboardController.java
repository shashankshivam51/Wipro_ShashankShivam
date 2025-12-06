package com.myfinbank.customer.controller;

import com.myfinbank.customer.model.Customer;
import com.myfinbank.customer.service.TransactionService;
import com.myfinbank.customer.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CustomerDashboardController {

    private final CustomerRepository customerRepository;
    private final TransactionService transactionService;

    public CustomerDashboardController(CustomerRepository customerRepository,
                                       TransactionService transactionService) {
        this.customerRepository = customerRepository;
        this.transactionService = transactionService;
    }

    @GetMapping("/customer/dashboard/{id}")
    public String customerDashboard(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            model.addAttribute("error", "Customer not found");
            return "customerDashboard";
        }

        model.addAttribute("customer", customer);

        if (customer.getAccountNo() != null && !customer.getAccountNo().isBlank()) {
            model.addAttribute("transactions",
                    transactionService.getRecentTransactions(customer.getAccountNo(), 5));
        }

        return "customerDashboard";
    }
}
