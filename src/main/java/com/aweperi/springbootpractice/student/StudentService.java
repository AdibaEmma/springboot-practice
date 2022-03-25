package com.aweperi.springbootpractice.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService implements IstudentService{
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> fetchStudents() {
        return List.of(
                new Student(1L,"Emmanuel", "eabaagah@gmail.com",
                        LocalDate.of(1990, Month.AUGUST, 15), 32)
        );
    }
}
