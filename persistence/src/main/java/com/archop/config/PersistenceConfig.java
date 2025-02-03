package com.archop.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.archop.entity")
@EnableJpaRepositories(basePackages = "com.archop.repository")
public class PersistenceConfig {
    // La configuration est gérée via application-persistence.properties
} 