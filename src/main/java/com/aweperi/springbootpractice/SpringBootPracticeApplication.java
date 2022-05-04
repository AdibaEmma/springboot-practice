package com.aweperi.springbootpractice;

import com.aweperi.springbootpractice.model.Role;
import com.aweperi.springbootpractice.repository.UserRoleRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

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
