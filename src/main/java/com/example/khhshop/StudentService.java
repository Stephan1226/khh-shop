package com.example.khhshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student addAge(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student modifiedStudent = student.get();
            modifiedStudent.setAge(modifiedStudent.getAge() + 1);
            return studentRepository.save(modifiedStudent);
        } else {
            return null;
        }
    }

    public Student updateAge(Long id, Integer age) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student modifiedStudent = student.get();
            modifiedStudent.setAge(age);
            return studentRepository.save(modifiedStudent);
        } else {
            return null;
        }
    }
}
