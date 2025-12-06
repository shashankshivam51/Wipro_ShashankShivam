package com.myfinbank.customer.service;

import com.myfinbank.customer.model.Customer;
import com.myfinbank.customer.model.Loan;
import com.myfinbank.customer.repository.CustomerRepository;
import com.myfinbank.customer.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    public LoanService(LoanRepository loanRepository,
                       CustomerRepository customerRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
    }

    public Loan applyLoan(Loan loan) {
        Customer c = customerRepository.findById(loan.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Customer not found: " + loan.getCustomerId()
                ));

        loan.setStatus("APPLIED");
        loan.setAppliedAt(LocalDateTime.now());
        return loanRepository.save(loan);
    }

    public List<Loan> getLoansForCustomer(Long customerId) {
        return loanRepository.findByCustomerIdOrderByAppliedAtDesc(customerId);
    }

    public List<Loan> getPendingLoans() {
        return loanRepository.findByStatusOrderByAppliedAtDesc("APPLIED");
    }
}
