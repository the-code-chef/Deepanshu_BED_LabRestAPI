package com.greatlearning.collegefest.service;

import com.greatlearning.collegefest.entity.Student;
import com.greatlearning.collegefest.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Integer studentId, Student student) throws Exception {
        Optional<Student> studentRepositoryById = studentRepository.findById(studentId);

        if (studentRepositoryById.isPresent()) {
            throw new Exception("OOPS!! student not present");
        }

        Student oldStudent = studentRepositoryById.get();
        if(student.getFirstName() != null)
            oldStudent.setFirstName(student.getFirstName());
        if(student.getLastName() != null)
            oldStudent.setLastName(student.getLastName());
        if(student.getCourse() != null)
            oldStudent.setCourse(student.getCourse());
        if(student.getCountry() != null)
            oldStudent.setCountry(student.getCountry());

        studentRepository.save(oldStudent);

        return oldStudent;
    }

    @Transactional
    @Override
    public Optional<Student> findById(int studentId) {
        return studentRepository.findById(studentId);
    }

    @Transactional
    @Override
    public void deleteById(int studentId) {
        studentRepository.deleteById(studentId);
    }
}
