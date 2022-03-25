package com.aweperi.springbootpractice.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/students/")
public class StudentController {
    @GetMapping
    public List<Student> fetchStudents() {
        return List.of(
                new Student(1L,"Emmanuel", "eabaagah@gmail.com",
                        LocalDate.of(1990, Month.AUGUST, 15), 32)
        );
    }
}
