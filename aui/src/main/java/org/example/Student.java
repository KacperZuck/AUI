package org.example;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Comparable<Student>, Serializable {

    private Long id;
    private String fullName;
    private double grade;
    private Course course;
    public Long getId() {
        return id;
    }
    public double getGrade() {
        return grade;
    }
    public Course getCourse() {
        return course;
    }
    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(fullName, student.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName);
    }

    @Override
    public int compareTo(Student other) {
        return this.fullName.compareToIgnoreCase(other.fullName);
    }

    @Override
    public String toString() {
        return "Student{id=%d, fullName='%s', grade=%.2f, course='%s'}"
                .formatted(id, fullName, grade,
                        (course != null ? course.getName() : "none"));

    }


}
