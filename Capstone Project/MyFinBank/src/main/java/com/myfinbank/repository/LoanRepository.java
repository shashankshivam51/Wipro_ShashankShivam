package com.myfinbank.repository;

import com.myfinbank.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByStatusOrderByAppliedAtDesc(String status);
    List<Loan> findByCustomerIdOrderByAppliedAtDesc(Long customerId);
}
