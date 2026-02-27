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
}
