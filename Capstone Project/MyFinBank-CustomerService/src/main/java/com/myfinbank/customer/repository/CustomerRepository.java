package com.myfinbank.customer.repository;

import com.myfinbank.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByUserName(String userName);

    Customer findByAccountNo(String accountNo);

    //  for Google login
    Customer findFirstByEmailOrderByIdAsc(String email);
}
