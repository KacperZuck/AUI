package org.example;
import lombok.AllArgsConstructor;
import lombok.Data;
public class StudentDTO {


    private Long id;
    private String fullName;
    private double grade;
    private String courseName;

    public StudentDTO(Long id, String fullName, double grade, String name) {
        this.courseName = name;
        this.id = id;
        this.fullName = fullName;
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "StudentDTO{id=%d, name='%s', grade=%.2f, course='%s'}"
                .formatted(id, fullName, grade, courseName);
    }

    public String getFullName() {
        return fullName;
    }
}
