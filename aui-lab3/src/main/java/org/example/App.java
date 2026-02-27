package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.example.service.StudentService;
import org.example.service.CourseService;

@SpringBootApplication(scanBasePackages = "org.example")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    /*
    @Bean
    @Transactional
    CommandLineRunner run(CourseRepository courseRepo, StudentRepository studentRepo) {

        return args -> {

            Course course1 = Course.builder()
                    .id(UUID.randomUUID())
                    .name("a1")
                    .description("Java programming basics")
                    .build();

            Course course2 = Course.builder()
                    .id(UUID.randomUUID())
                    .name("a2")
                    .description("Arrays, lists, trees")
                    .build();

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

            course1.getStudents().addAll(List.of(s1, s2));
            course2.getStudents().add(s3);


            courseRepo.saveAll(List.of(course1, course2));

            List<Student> studentsInProgramming = studentRepo.findByCourse(course1.getId());
            studentsInProgramming.forEach(System.out::println);
        };

    }
    *.
     */
}
