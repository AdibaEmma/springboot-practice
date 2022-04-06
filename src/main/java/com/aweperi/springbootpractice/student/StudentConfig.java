package com.aweperi.springbootpractice.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            var emma = new Student(
                    1L,
                    "Emmanuel Adiba",
                    "eabaagah@gmail.com",
                    LocalDate.of(1990, Month.AUGUST, 15)
            );

            var sam = new Student(
                    2L,
                    "Sam Aweperi",
                    "raweperi94@gmail.com",
                    LocalDate.of(1987, Month.JULY, 9)
            );

            var bright = new Student(
                    3L,
                    "Bright Smith",
                    "smithb@gmail.com",
                    LocalDate.of(1992, Month.JANUARY, 20)
            );
            studentRepository.saveAll(List.of(emma, sam, bright));
        };
    }
}
