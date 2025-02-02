package com.archop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.archop"})
public class ArchopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArchopApplication.class, args);
    }
} 