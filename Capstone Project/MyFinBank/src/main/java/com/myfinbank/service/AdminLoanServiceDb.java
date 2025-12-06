package com.myfinbank.service;

import com.myfinbank.model.Loan;
import com.myfinbank.model.Customer;
import com.myfinbank.model.Transaction;
import com.myfinbank.repository.LoanRepository;
import com.myfinbank.repository.CustomerRepository;
import com.myfinbank.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AdminLoanServiceDb {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public AdminLoanServiceDb(LoanRepository loanRepository,
                              CustomerRepository customerRepository,
                              TransactionRepository transactionRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    // ---------- Read operations ----------
    public List<Loan> fetchPendingLoans() {
        return loanRepository.findByStatusOrderByAppliedAtDesc("APPLIED");
    }

    public List<Loan> fetchLoansByCustomer(Long customerId) {
        return loanRepository.findByCustomerIdOrderByAppliedAtDesc(customerId);
    }

    // ---------- Counts ----------
    public long countByStatus(String status) {
        if (status == null) return 0L;
        return loanRepository.findByStatusOrderByAppliedAtDesc(status.toUpperCase()).size();
    }

    public long countPendingLoans() { return countByStatus("APPLIED"); }
    public long countApprovedLoans() { return countByStatus("APPROVED"); }
    public long countRejectedLoans() { return countByStatus("REJECTED"); }
    public long countAllLoans() { return loanRepository.count(); }

    // ---------- Approve / Reject ----------
    @Transactional
    public boolean approveLoan(Long loanId, Long adminId) {
        Loan loan = loanRepository.findById(loanId).orElse(null);
        if (loan == null) return false;
        if (!"APPLIED".equalsIgnoreCase(loan.getStatus())) return false;

        // mark approved
        loan.setStatus("APPROVED");
        loan.setApprovedAt(LocalDateTime.now());
        loan.setApprovedByAdminId(adminId);
        loanRepository.save(loan);

        // disburse to customer
        Customer customer = customerRepository.findById(loan.getCustomerId()).orElse(null);
        if (customer == null) {
            throw new IllegalStateException("Customer not found for loan id " + loanId);
        }
        double currBal = customer.getAmount() == null ? 0.0 : customer.getAmount();
        double disbursal = loan.getAmount() == null ? 0.0 : loan.getAmount();
        double newBal = currBal + disbursal;
        customer.setAmount(newBal);
        customerRepository.save(customer);

        // write transaction record
        Transaction tx = new Transaction();
        tx.setTransactionId(generateTransactionId());
        tx.setAccountNo(customer.getAccountNo());
        tx.setTransactionType("LOAN_DISBURSAL");
        tx.setAmount(disbursal);
        tx.setBalanceAfter(newBal);
        tx.setRemarks("Loan disbursal for loan id " + loan.getId());
        tx.setTransactionTime(LocalDateTime.now());
        transactionRepository.save(tx);

        return true;
    }

    @Transactional
    public boolean rejectLoan(Long loanId, Long adminId, String reason) {
        Loan loan = loanRepository.findById(loanId).orElse(null);
        if (loan == null) return false;
        if (!"APPLIED".equalsIgnoreCase(loan.getStatus())) return false;

        loan.setStatus("REJECTED");
        loan.setApprovedAt(LocalDateTime.now());
        loan.setApprovedByAdminId(adminId);
        loan.setRejectionReason(reason == null ? "" : reason);
        loanRepository.save(loan);

        return true;
    }

    // ---------- Helpers ----------
    private String generateTransactionId() {
        return "TXN-" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
    }
}
