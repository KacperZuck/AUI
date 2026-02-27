package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id; // UUID generowany po stronie klienta

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "grade")
    private double grade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @Override
    public String toString() {
        // Nie wywołujemy course.getName() aby uniknąć LazyInitializationException
        return "Student{id=" + id + ", fullName='" + fullName + "', grade=" + grade + "}";
    }

}
