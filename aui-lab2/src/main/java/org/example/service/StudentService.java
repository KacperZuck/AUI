package org.example.service;

import jakarta.transaction.Transactional;
import org.example.entity.Student;
import org.example.entity.Course;
import org.example.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(UUID id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteById(UUID id) {
        studentRepository.deleteById(id);

    }

    public List<Student> findByCourse(Course course) {
        return studentRepository.findByCourse(course);
    }
}
