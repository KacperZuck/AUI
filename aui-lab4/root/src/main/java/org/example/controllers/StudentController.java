package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.DTOcreating.StudentDTO;
import org.example.DTOread.StudentReadDTO;
import org.example.DTOreadCollecion.StudentCollectionDTO;
import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<StudentCollectionDTO>> getAllStudents() {
        var students = studentService.findAll()
                .stream()
                .map(s -> new StudentCollectionDTO(s.getId(), s.getFullName()))
                .toList();

        if (students.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(students);
    }

    @GetMapping("/courses/{courseId}/students")
    public ResponseEntity<List<StudentReadDTO>> getStudentsByCourse(@PathVariable UUID courseId) {
        var list = studentService.findByCourse(courseId);
        if (list == null)
            return ResponseEntity.notFound().build(); // kurs nie istnieje
        if (list.isEmpty())
            return ResponseEntity.noContent().build(); // kurs istnieje, ale pusty

        var dtoList = list.stream()
                .map(s -> new StudentReadDTO(s.getId(), s.getFullName(), s.getGrade(), s.getCourse().getName()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/courses/{courseId}/students")
    public ResponseEntity<StudentReadDTO> addStudentToCourse(@PathVariable UUID courseId,
                                                             @RequestBody StudentDTO dto) {
        Student student = new Student();
        student.setId(UUID.randomUUID());
        student.setFullName(dto.getFullName());
        student.setGrade(dto.getGrade());

        var savedOpt = studentService.addToCourse(courseId, student);
        if (savedOpt.isEmpty())
            return ResponseEntity.notFound().build(); // kurs nie istnieje

        var saved = savedOpt.get();
        return ResponseEntity.created(URI.create("/api/students/" + saved.getId()))
                .body(new StudentReadDTO(saved.getId(), saved.getFullName(), saved.getGrade(), saved.getCourse().getName()));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentReadDTO> updateStudent(@PathVariable UUID id,
                                                        @RequestBody StudentDTO dto) {
        return studentService.update(id, dto.getGrade(), dto.getFullName())
                .map(s -> ResponseEntity.ok(new StudentReadDTO(s.getId(), s.getFullName(), s.getGrade(), s.getCourse().getName())))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        if (!studentService.delete(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
