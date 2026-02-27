package org.example.service;

import org.example.entity.Course;
import org.example.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Delegacja metod repozytorium
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(UUID id) {
        return courseRepository.findById(id);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public void deleteById(UUID id) {
        courseRepository.deleteById(id);
    }
}
