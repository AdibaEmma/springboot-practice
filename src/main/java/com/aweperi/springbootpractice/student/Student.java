package com.aweperi.springbootpractice.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor()
@Data
public class Student {
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private Integer age;
}
