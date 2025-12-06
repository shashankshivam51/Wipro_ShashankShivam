package com.myfinbank.controller;

import com.myfinbank.model.Admin;
import com.myfinbank.model.Customer;
import com.myfinbank.repository.AdminRepository;
import com.myfinbank.repository.CustomerRepository;
import com.myfinbank.service.AdminLoanServiceDb;
import com.myfinbank.model.Loan;
import com.myfinbank.mail.MailSend;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;
    private final AdminLoanServiceDb adminLoanServiceDb;

    // Constructor - include AdminLoanServiceDb along with your existing repositories
    public AdminController(CustomerRepository customerRepository,
                           AdminRepository adminRepository,
                           AdminLoanServiceDb adminLoanServiceDb) {
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
        this.adminLoanServiceDb = adminLoanServiceDb;
    }

    // ---------- Dashboard ----------
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        long totalCustomers = customerRepository.count();
        long inactiveCustomers = customerRepository.findByStatus("INACTIVE").size();

        model.addAttribute("totalCustomers", totalCustomers);
        model.addAttribute("inactiveCustomers", inactiveCustomers);

        return "adminDashboard";
    }

    // ---------- Inactive Customers / Activate ----------
    @GetMapping("/inactive-customers")
    public String showInactiveCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findByStatus("INACTIVE"));
        return "adminInactiveCustomers";
    }

    @PostMapping("/activate/{id}")
    public String activateCustomer(@PathVariable Long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            Customer customer = optional.get();
            if (customer.getAccountNo() == null || customer.getAccountNo().isBlank()) {
                customer.setAccountNo(generateAccountNo(customer));
            }
            customer.setStatus("ACTIVE");
            if (customer.getDateOfOpen() == null) {
                customer.setDateOfOpen(LocalDate.now());
            }
            customerRepository.save(customer);

            // ---- send welcome email if email is present ----
            if (customer.getEmail() != null && !customer.getEmail().isBlank()) {
                String subject = "Welcome to MyFinBank";
                String body = "Dear " + customer.getFirstName() + ",\n\n"
                        + "Your account has been activated successfully.\n"
                        + "Account Number: " + customer.getAccountNo() + "\n"
                        + "Current Balance: " + customer.getAmount() + "\n\n"
                        + "Thank you for banking with MyFinBank.\n"
                        + "Regards,\n"
                        + "MyFinBank Team";

                MailSend.sendMail(customer.getEmail(), subject, body);
            }
        }
        return "redirect:/admin/inactive-customers";
    }

    private String generateAccountNo(Customer customer) {
        long base = 1000000000L;
        return String.valueOf(base + customer.getId());
    }

    // ---------- Show All Customers ----------
    @GetMapping("/customers")
    public String showAllCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "adminAllCustomers";
    }

    // ---------- Search / View Specific Customer ----------
    @GetMapping("/search")
    public String showSearchForm(Model model) {
        return "searchCustomer";
    }

    @PostMapping("/search-customer")
    public String searchCustomer(@RequestParam("accountNo") String accountNo,
                                 Model model) {

        Customer customer = customerRepository.findByAccountNo(accountNo);

        if (customer == null) {
            model.addAttribute("error", "No customer found with account number: " + accountNo);
            return "searchCustomer";
        }

        model.addAttribute("customer", customer);
        return "viewCustomer";
    }

    // ---------- Create New Admin ----------
    @GetMapping("/add-admin")
    public String showAddAdminForm(Model model) {
        return "addAdmin";
    }

    @PostMapping("/add-admin")
    public String saveAdmin(@RequestParam("adminName") String adminName,
                            @RequestParam("adminUserName") String adminUserName,
                            @RequestParam("adminPassword") String adminPassword,
                            @RequestParam("adminEmail") String adminEmail,
                            @RequestParam("adminPhone") String adminPhone,
                            @RequestParam("adminStatus") String adminStatus,
                            Model model) {

        com.myfinbank.model.Admin admin = new com.myfinbank.model.Admin();
        admin.setAdminName(adminName);
        admin.setAdminUserName(adminUserName);
        admin.setAdminPassword(adminPassword);
        admin.setAdminEmail(adminEmail);
        admin.setAdminPhone(adminPhone);
        admin.setAdminStatus(adminStatus);

        adminRepository.save(admin);

        model.addAttribute("success", "Admin created successfully!");
        return "addAdmin";
    }

    @GetMapping("/customer/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isEmpty()) {
            return "redirect:/admin/customers";
        }
        model.addAttribute("customer", optional.get());
        return "editCustomer";
    }

    @PostMapping("/customer/update")
    public String updateCustomer(@ModelAttribute("customer") Customer customer,
                                 RedirectAttributes redirectAttributes) {

        customerRepository.save(customer);

        // ---- low balance email check ----
        if (customer.getAmount() != null
                && customer.getAmount() < 1000
                && customer.getEmail() != null
                && !customer.getEmail().isBlank()) {

            String subject = "Low Balance Alert - MyFinBank";
            String body = "Dear " + customer.getFirstName() + ",\n\n"
                    + "This is to inform you that your account balance is low.\n"
                    + "Account Number: " + customer.getAccountNo() + "\n"
                    + "Current Balance: " + customer.getAmount() + "\n\n"
                    + "Please maintain sufficient balance to avoid any inconvenience.\n\n"
                    + "Regards,\n"
                    + "MyFinBank Team";

            MailSend.sendMail(customer.getEmail(), subject, body);
        }

        redirectAttributes.addFlashAttribute("message", "Customer updated successfully.");
        return "redirect:/admin/customers";
    }

    // ---------- NEW: Pending Loans / Approve / Reject (DB-backed) ----------

    /**
     * Show pending loans by fetching them from the shared DB.
     */
    @GetMapping("/loans/pending")
    public String viewPendingLoans(Model model, HttpServletRequest request) {
        List<Loan> pending = adminLoanServiceDb.fetchPendingLoans();
        model.addAttribute("pendingLoans", pending);
        model.addAttribute("pendingLoanCount", pending.size());

        // Carry over any session messages (if present)
        Object err = request.getSession().getAttribute("error");
        if (err != null) {
            model.addAttribute("error", err);
            request.getSession().removeAttribute("error");
        }
        Object suc = request.getSession().getAttribute("success");
        if (suc != null) {
            model.addAttribute("success", suc);
            request.getSession().removeAttribute("success");
        }

        return "adminPendingLoans";
    }

    /**
     * Approve a loan. Marks loan approved, disburses amount and writes transaction.
     */
    @PostMapping("/loans/{id}/approve")
    public String approveLoan(@PathVariable("id") Long id, HttpServletRequest request) {
        Long adminId = resolveAdminIdFromSession(request);
        if (adminId == null) {
            request.getSession().setAttribute("error", "Admin identity not found in session.");
            return "redirect:/admin/loans/pending";
        }

        try {
            boolean ok = adminLoanServiceDb.approveLoan(id, adminId);
            if (!ok) {
                request.getSession().setAttribute("error", "Failed to approve loan (DB operation).");
            } else {
                request.getSession().setAttribute("success", "Loan approved and disbursed.");
            }
        } catch (Exception ex) {
            request.getSession().setAttribute("error", "Error approving loan: " + ex.getMessage());
        }
        return "redirect:/admin/loans/pending";
    }

    /**
     * Reject a loan with optional reason.
     */
    @PostMapping("/loans/{id}/reject")
    public String rejectLoan(@PathVariable("id") Long id,
                             @RequestParam(value = "reason", required = false) String reason,
                             HttpServletRequest request) {

        Long adminId = resolveAdminIdFromSession(request);
        if (adminId == null) {
            request.getSession().setAttribute("error", "Admin identity not found in session.");
            return "redirect:/admin/loans/pending";
        }

        boolean ok = adminLoanServiceDb.rejectLoan(id, adminId, reason);
        if (!ok) {
            request.getSession().setAttribute("error", "Failed to reject loan (DB operation).");
        } else {
            request.getSession().setAttribute("success", "Loan rejected successfully.");
        }
        return "redirect:/admin/loans/pending";
    }

    // Helper: resolve admin id from session (tries "adminId" numeric or "admin" object)
    private Long resolveAdminIdFromSession(HttpServletRequest request) {
        Object adminIdObj = request.getSession().getAttribute("adminId");
        if (adminIdObj instanceof Number) {
            return ((Number) adminIdObj).longValue();
        }
        Object adminObj = request.getSession().getAttribute("admin");
        if (adminObj instanceof Admin) {
            return ((Admin) adminObj).getAdminId();
        }
        return null;
    }
}
