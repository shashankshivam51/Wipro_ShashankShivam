package com.myfinbank.controller;

import com.myfinbank.model.Admin;
import com.myfinbank.repository.AdminRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminAuthController {

    private final AdminRepository adminRepository;

    public AdminAuthController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "adminLogin";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model,
                               HttpSession session) {

        Admin admin = adminRepository.findByAdminUserName(username);

        if (admin == null || !admin.getAdminPassword().equals(password)) {
            model.addAttribute("error", "Invalid username or password");
            return "adminLogin";
        }

        if (admin.getAdminStatus() == null ||
                !"ACTIVE".equalsIgnoreCase(admin.getAdminStatus())) {
            model.addAttribute("error", "Admin is not active");
            return "adminLogin";
        }

        // store both adminId and admin object in session so AdminController can resolve identity
        session.setAttribute("adminId", admin.getAdminId());
        session.setAttribute("admin", admin);

        // keep legacy attribute (optional) used by other views
        session.setAttribute("loggedInAdmin", admin.getAdminUserName());

        return "redirect:/admin/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
