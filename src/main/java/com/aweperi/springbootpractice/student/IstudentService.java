package com.aweperi.springbootpractice.student;

import java.util.List;

public interface IstudentService {
    public List<Student> fetchStudents();

    void addNewStudent(Student student) throws StudentException;

    void deleteStudent(Long studentId) throws StudentException;

    void updateStudent(Long studentId, String name, String email) throws StudentException;
}
