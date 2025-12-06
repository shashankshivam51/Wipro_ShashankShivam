package com.myfinbank.customer.repository;

import com.myfinbank.customer.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByCustomerIdOrderByAppliedAtDesc(Long customerId);

    List<Loan> findByStatusOrderByAppliedAtDesc(String status);
}
