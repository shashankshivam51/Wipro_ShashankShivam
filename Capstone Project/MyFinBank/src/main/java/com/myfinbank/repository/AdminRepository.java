package com.myfinbank.repository;

import com.myfinbank.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByAdminUserName(String adminUserName);
    
}
