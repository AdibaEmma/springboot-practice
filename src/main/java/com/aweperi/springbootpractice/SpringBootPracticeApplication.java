package com.aweperi.springbootpractice;

import com.aweperi.springbootpractice.model.Role;
import com.aweperi.springbootpractice.repository.UserRoleRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition
public class SpringBootPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPracticeApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRoleRepository userRoleRepository) {
        return args -> {
            userRoleRepository.save(new Role(null, "USER"));
            userRoleRepository.save(new Role(null, "ADMIN"));
            userRoleRepository.save(new Role(null, "SYSTEM"));
        };
    }
}
