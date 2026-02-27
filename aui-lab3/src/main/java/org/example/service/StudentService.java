package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.entity.Student;
import org.example.entity.Course;
import org.example.repository.CourseRepository;
import org.example.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findByCourse(UUID courseId) {
        return courseRepository.findById(courseId)
                .map(Course::getStudents)
                .orElse(null);
    }

    public Optional<Student> findById(UUID id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> update(UUID id, double grade, String fullName) {
        return studentRepository.findById(id).map(existing -> {
            existing.setFullName(fullName);
            existing.setGrade(grade);
            return studentRepository.save(existing);
        });
    }

    public boolean delete(UUID id) {
        var studentO = studentRepository.findById(id);
        if( studentO.isEmpty() ) {
            return false;
        }

        var course = studentO.get().getCourse();

        if( course != null ) {
            course.getStudents().remove(studentO.get());
        }
        studentRepository.delete(studentO.get());
        return true;
    }

    @Transactional
    public Optional<Student> addToCourse(UUID courseId, Student student) {
        return courseRepository.findById(courseId).map(course -> {
            student.setCourse(course);
            course.getStudents().add(student);
            courseRepository.save(course);
            return student;
        });
    }
}
