package com.myfinbank.customer.controller;

import com.myfinbank.customer.model.Customer;
import com.myfinbank.customer.model.Loan;
import com.myfinbank.customer.service.LoanService;
import com.myfinbank.customer.repository.CustomerRepository;
import com.myfinbank.customer.util.FinanceUtils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer/loans")
public class CustomerLoanController {

    private final LoanService loanService;
    private final CustomerRepository customerRepository;

    public CustomerLoanController(LoanService loanService,
                                  CustomerRepository customerRepository) {
        this.loanService = loanService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/emi")
    @ResponseBody
    public ResponseEntity<?> calculateEmi(@RequestParam double principal,
                                          @RequestParam double annualRate,
                                          @RequestParam int months) {

        try {
            double emi = FinanceUtils.calculateEmi(principal, annualRate, months);
            return ResponseEntity.ok(Map.of("emi", emi));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    @PostMapping("/apply")
    public String applyLoan(@ModelAttribute Loan loan,
                            @RequestParam Long customerId,
                            HttpServletRequest request) {

        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer == null) {
            request.getSession().setAttribute("error", "Customer not found.");
            return "redirect:/customer/dashboard/" + customerId;
        }

        loan.setCustomerId(customerId);
        loanService.applyLoan(loan);

        request.getSession().setAttribute(
                "success",
                "Loan application submitted and will be reviewed by admin."
        );

        return "redirect:/customer/dashboard/" + customerId;
    }

    @GetMapping("/my")
    public String myLoans(@RequestParam Long customerId, Model model) {

        List<Loan> loans = loanService.getLoansForCustomer(customerId);
        model.addAttribute("loans", loans);

        return "myLoans";
    }
}
