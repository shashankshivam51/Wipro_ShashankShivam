package com.myfinbank.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MyfinbankCustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyfinbankCustomerServiceApplication.class, args);
    }
}
