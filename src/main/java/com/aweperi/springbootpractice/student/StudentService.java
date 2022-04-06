package com.aweperi.springbootpractice.student;

import lombok.SneakyThrows;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService implements IstudentService{
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> fetchStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addNewStudent(Student student) throws StudentException {
        var emailExists = studentRepository.findStudentByEmail(student.getEmail()).isPresent();
        if(emailExists) {
            throw new StudentException(new StudentEmailExistsException());
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) throws StudentException {
        var studentExists = studentRepository.existsById(studentId);
        if(!studentExists) {
            throw new StudentException(new InvalidIDException());
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    @Override
    public void updateStudent(Long studentId, String name, String email) throws StudentException {
        var student = studentRepository.findById(studentId).orElseThrow(() -> new StudentException(new InvalidIDException()));
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name))
            student.setName(name);

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            var emailExists = studentRepository.findStudentByEmail(email).isPresent();
            if(emailExists) throw new StudentException(new InvalidIDException());
            student.setEmail(email);
        }

    }
}
