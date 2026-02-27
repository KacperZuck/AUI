package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Course;
import org.example.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.example.controllers.CourseController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
        System.out.println(">>> CourseService created");
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(UUID id) {
        return courseRepository.findById(id);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public void delete(UUID id) {
        courseRepository.deleteById(id);
    }

    public boolean exists(UUID id) {
        return courseRepository.existsById(id);
    }
}
