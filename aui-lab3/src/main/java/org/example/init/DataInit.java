package org.example.init;

import jakarta.annotation.PostConstruct;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.service.CourseService;
import org.example.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInit {


    private final CourseService courseService;
    private final StudentService studentService;

    public DataInit(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @PostConstruct
    public void init() {
        Course course1 = Course.builder()
                .id(UUID.randomUUID())
                .name("Programming")
                .description("Java programming basics")
                .build();

        Course course2 = Course.builder()
                .id(UUID.randomUUID())
                .name("Data Structures")
                .description("Arrays, lists, trees")
                .build();

        courseService.save(course1);
        courseService.save(course2);

        Student s1 = Student.builder()
                .id(UUID.randomUUID())
                .fullName("Alice Johnson")
                .grade(4.5)
                .course(course1)
                .build();

        Student s2 = Student.builder()
                .id(UUID.randomUUID())
                .fullName("Bob Smith")
                .grade(3.8)
                .course(course1)
                .build();

        Student s3 = Student.builder()
                .id(UUID.randomUUID())
                .fullName("Charlie Brown")
                .grade(4.2)
                .course(course2)
                .build();

        studentService.addToCourse(course1.getId(), s1);
        studentService.addToCourse(course1.getId(), s2);
        studentService.addToCourse(course2.getId(), s3);
    }
}
