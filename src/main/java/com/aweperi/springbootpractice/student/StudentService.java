package com.aweperi.springbootpractice.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService implements IstudentService{
    @Override
    public List<Student> fetchStudents() {
        return List.of(
                new Student(1L,"Emmanuel", "eabaagah@gmail.com",
                        LocalDate.of(1990, Month.AUGUST, 15), 32)
        );
    }
}
