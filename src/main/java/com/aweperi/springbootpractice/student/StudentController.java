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
    private final IstudentService istudentService;

    public StudentController(IstudentService istudentService) {
        this.istudentService = istudentService;
    }

    @GetMapping
    public List<Student> fetchStudents() {
        return istudentService.fetchStudents();
    }
}
