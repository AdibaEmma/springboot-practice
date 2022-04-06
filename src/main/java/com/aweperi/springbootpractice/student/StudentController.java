package com.aweperi.springbootpractice.student;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void createStudent(@RequestBody Student student) {
        try {
            istudentService.addNewStudent(student);
        } catch (StudentException e) {
            var cause = e.getCause();
            System.out.println(cause.getMessage());
        }
    }

    @PutMapping("{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        try {
            istudentService.updateStudent(studentId, name, email);
        } catch (StudentException e) {
            var cause = e.getCause();
            System.out.println(cause.getMessage());
        }

    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        try {
            istudentService.deleteStudent(studentId);
        } catch (StudentException e) {
            var cause = e.getCause();
            System.out.println(cause.getMessage());
        }
    }
}
