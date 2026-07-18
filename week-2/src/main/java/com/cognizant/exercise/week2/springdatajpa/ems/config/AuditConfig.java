package com.cognizant.exercise.week2.springdatajpa.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "emsAuditorProvider")
public class AuditConfig {

    @Bean
    public AuditorAware<String> emsAuditorProvider() {
        return () -> Optional.of("EMSAdminUser");
    }
}
