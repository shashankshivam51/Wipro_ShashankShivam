package com.myfinbank.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@ComponentScan(basePackages = "com.myfinbank")
@EnableJpaRepositories(basePackages = "com.myfinbank.repository")
@EntityScan(basePackages = "com.myfinbank.model")
public class MyFinBankNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFinBankNewApplication.class, args);
    }
}
