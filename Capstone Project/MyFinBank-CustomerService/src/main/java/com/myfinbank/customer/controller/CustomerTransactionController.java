package com.myfinbank.customer.controller;

import com.myfinbank.customer.model.Customer;
import com.myfinbank.customer.model.Transaction;
import com.myfinbank.customer.repository.CustomerRepository;
import com.myfinbank.customer.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerTransactionController {

    private final CustomerRepository customerRepository;
    private final TransactionService transactionService;

    public CustomerTransactionController(CustomerRepository customerRepository,
                                         TransactionService transactionService) {
        this.customerRepository = customerRepository;
        this.transactionService = transactionService;
    }

    @PostMapping("/customer/deposit")
    public String deposit(@RequestParam("amount") Double amount,
                          @RequestParam(value = "remarks", required = false) String remarks,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        Long customerId = (Long) session.getAttribute("loggedInCustomerId");
        if (customerId == null) {
            return "redirect:/customer/login";
        }

        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            session.invalidate();
            return "redirect:/customer/login";
        }

        try {
            Transaction tx = transactionService.deposit(customerId, amount, remarks);
            redirectAttributes.addFlashAttribute(
                    "success",
                    "Deposit successful. Transaction ID: " + tx.getTransactionId()
            );
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }

        return "redirect:/customer/dashboard/" + customer.getId();
    }

    @PostMapping("/customer/withdraw")
    public String withdraw(@RequestParam("amount") Double amount,
                           @RequestParam(value = "remarks", required = false) String remarks,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {

        Long customerId = (Long) session.getAttribute("loggedInCustomerId");
        if (customerId == null) {
            return "redirect:/customer/login";
        }

        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            session.invalidate();
            return "redirect:/customer/login";
        }

        try {
            Transaction tx = transactionService.withdraw(customerId, amount, remarks);
            redirectAttributes.addFlashAttribute(
                    "success",
                    "Withdrawal successful. Transaction ID: " + tx.getTransactionId()
            );
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }

        return "redirect:/customer/dashboard/" + customer.getId();
    }

    @PostMapping("/customer/transfer")
    public String transfer(@RequestParam("toAccountNo") String toAccountNo,
                           @RequestParam("amount") Double amount,
                           @RequestParam(value = "remarks", required = false) String remarks,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {

        Long customerId = (Long) session.getAttribute("loggedInCustomerId");
        if (customerId == null) {
            return "redirect:/customer/login";
        }

        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            session.invalidate();
            return "redirect:/customer/login";
        }

        try {
            Transaction tx = transactionService.transfer(customerId, toAccountNo, amount, remarks);
            redirectAttributes.addFlashAttribute(
                    "success",
                    "Transfer successful. Transaction ID: " + tx.getTransactionId()
            );
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }

        return "redirect:/customer/dashboard/" + customer.getId();
    }
}
