package com.myfinbank.customer.service;

import com.myfinbank.customer.mail.MailSend;
import com.myfinbank.customer.model.Customer;
import com.myfinbank.customer.model.Transaction;
import com.myfinbank.customer.repository.CustomerRepository;
import com.myfinbank.customer.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;
    private final MailSend mailSend;

    public TransactionService(TransactionRepository transactionRepository,
                              CustomerRepository customerRepository,
                              MailSend mailSend) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
        this.mailSend = mailSend;
    }

    // ---------- Public methods used by controllers ----------

    @Transactional
    public Transaction deposit(Long customerId, Double amount, String remarks) {
        validateAmountPositive(amount);
        String finalRemarks = (remarks == null || remarks.isBlank())
                ? "Deposit of amount " + amount
                : remarks;
        return recordTransaction(customerId, "DEPOSIT", amount, finalRemarks);
    }

    @Transactional
    public Transaction withdraw(Long customerId, Double amount, String remarks) {
        validateAmountPositive(amount);
        String finalRemarks = (remarks == null || remarks.isBlank())
                ? "Withdrawal of amount " + amount
                : remarks;
        return recordTransaction(customerId, "WITHDRAW", amount, finalRemarks);
    }

    @Transactional
    public Transaction transfer(Long fromCustomerId,
                                String toAccountNo,
                                Double amount,
                                String remarks) {

        validateAmountPositive(amount);

        if (toAccountNo == null || toAccountNo.isBlank()) {
            throw new IllegalArgumentException("Destination account number is required");
        }

        Customer fromCustomer = customerRepository.findById(fromCustomerId)
                .orElseThrow(() -> new IllegalArgumentException("Source customer not found"));

        Customer toCustomer = customerRepository.findByAccountNo(toAccountNo);
        if (toCustomer == null) {
            throw new IllegalArgumentException("Destination account not found");
        }

        if (!isActiveWithAccount(fromCustomer) || !isActiveWithAccount(toCustomer)) {
            throw new IllegalStateException("Both accounts must be ACTIVE with valid account numbers");
        }

        double fromBalance = fromCustomer.getAmount() == null ? 0.0 : fromCustomer.getAmount();
        if (fromBalance < amount) {
            throw new IllegalStateException("Insufficient balance in source account");
        }

        double toBalance = toCustomer.getAmount() == null ? 0.0 : toCustomer.getAmount();

        // Update balances
        fromCustomer.setAmount(fromBalance - amount);
        toCustomer.setAmount(toBalance + amount);

        customerRepository.save(fromCustomer);
        customerRepository.save(toCustomer);

        String finalRemarks = (remarks == null || remarks.isBlank())
                ? "Transfer to account " + toAccountNo + " of amount " + amount
                : remarks;

        // Create a transaction entry for the source account
        Transaction tx = new Transaction();
        tx.setTransactionId(generateTransactionId());
        tx.setAccountNo(fromCustomer.getAccountNo());
        tx.setTransactionType("TRANSFER");
        tx.setAmount(amount);
        tx.setBalanceAfter(fromCustomer.getAmount());
        tx.setRemarks(finalRemarks);
        tx.setTransactionTime(LocalDateTime.now());

        Transaction saved = transactionRepository.save(tx);

        // Email to source account holder
        sendTransactionEmail(fromCustomer, saved);

        // Optional: Email to destination account holder
        sendCreditEmail(toCustomer, amount, toCustomer.getAmount(), fromCustomer.getAccountNo());

        return saved;
    }

    public List<Transaction> getRecentTransactions(String accountNo, int limit) {
        List<Transaction> all =
                transactionRepository.findByAccountNoOrderByTransactionTimeDesc(accountNo);
        if (all.size() <= limit) {
            return all;
        }
        return all.subList(0, limit);
    }

    // ---------- Core logic reused internally ----------

    @Transactional
    public Transaction recordTransaction(Long customerId,
                                         String transactionType,
                                         Double amount,
                                         String remarks) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        if (!isActiveWithAccount(customer)) {
            throw new IllegalStateException("Customer account is not active or has no account number");
        }

        double currentBalance = customer.getAmount() == null ? 0.0 : customer.getAmount();
        double newBalance = currentBalance;

        if ("DEPOSIT".equalsIgnoreCase(transactionType)) {
            newBalance = currentBalance + amount;
        } else if ("WITHDRAW".equalsIgnoreCase(transactionType)) {
            if (currentBalance < amount) {
                throw new IllegalStateException("Insufficient balance");
            }
            newBalance = currentBalance - amount;
        } else {
            throw new IllegalArgumentException("Unsupported transaction type: " + transactionType);
        }

        // Update balance in customer
        customer.setAmount(newBalance);
        customerRepository.save(customer);

        // Create transaction record
        Transaction tx = new Transaction();
        tx.setTransactionId(generateTransactionId());
        tx.setAccountNo(customer.getAccountNo());
        tx.setTransactionType(transactionType.toUpperCase());
        tx.setAmount(amount);
        tx.setBalanceAfter(newBalance);
        tx.setRemarks(remarks);
        tx.setTransactionTime(LocalDateTime.now());

        Transaction saved = transactionRepository.save(tx);

        // Send email notification
        sendTransactionEmail(customer, saved);

        return saved;
    }

    // ---------- Helper methods ----------

    private boolean isActiveWithAccount(Customer customer) {
        return customer.getStatus() != null
                && "ACTIVE".equalsIgnoreCase(customer.getStatus())
                && customer.getAccountNo() != null
                && !customer.getAccountNo().isBlank();
    }

    private void validateAmountPositive(Double amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }

    private String generateTransactionId() {
        return "TXN-" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 12)
                .toUpperCase();
    }

    private void sendTransactionEmail(Customer customer, Transaction tx) {
        if (customer.getEmail() == null || customer.getEmail().isBlank()) {
            return;
        }

        String subject = "Transaction Alert - " + tx.getTransactionType();
        String body = "Dear " + customer.getFirstName() + ",\n\n"
                + "A transaction has been performed on your MyFinBank account.\n\n"
                + "Transaction ID : " + tx.getTransactionId() + "\n"
                + "Account Number : " + tx.getAccountNo() + "\n"
                + "Type           : " + tx.getTransactionType() + "\n"
                + "Amount         : " + tx.getAmount() + "\n"
                + "Balance After  : " + tx.getBalanceAfter() + "\n"
                + "Time           : " + tx.getTransactionTime() + "\n\n"
                + "If you did not authorize this transaction, please contact the bank immediately.\n\n"
                + "Regards,\n"
                + "MyFinBank Team";

        mailSend.sendMail(customer.getEmail(), subject, body);
    }

    private void sendCreditEmail(Customer customer,
                                 Double amountCredited,
                                 Double newBalance,
                                 String fromAccountNo) {
        if (customer.getEmail() == null || customer.getEmail().isBlank()) {
            return;
        }

        String subject = "Amount Credited to Your Account";
        String body = "Dear " + customer.getFirstName() + ",\n\n"
                + "An amount has been credited to your MyFinBank account.\n\n"
                + "From Account  : " + fromAccountNo + "\n"
                + "To Account    : " + customer.getAccountNo() + "\n"
                + "Amount        : " + amountCredited + "\n"
                + "New Balance   : " + newBalance + "\n\n"
                + "Thank you for banking with MyFinBank.\n\n"
                + "Regards,\n"
                + "MyFinBank Team";

        mailSend.sendMail(customer.getEmail(), subject, body);
    }
}
