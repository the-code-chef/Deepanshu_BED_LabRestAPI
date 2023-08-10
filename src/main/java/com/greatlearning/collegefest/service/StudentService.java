package com.greatlearning.collegefest.service;

import com.greatlearning.collegefest.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {
    public void save(Student student);

    public List<Student> findAll();

    public Student updateStudent(Integer studentId, Student student) throws Exception;

    public Optional<Student> findById(int studentId);

    public void deleteById(int studentId);
}
