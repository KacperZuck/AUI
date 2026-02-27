package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.DTOcreating.CourseDTO;
import org.example.DTOread.CourseReadDTO;
import org.example.DTOreadCollecion.CourseCollectionDTO;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
public class CourseController {


    private final CourseService service;

    public CourseController(CourseService service) {
        System.out.println(">>> CourseController created, service = " + service);
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CourseCollectionDTO>> getAllCourses() {
        var courses = service.findAll()
                .stream()
                .map(c -> new CourseCollectionDTO(c.getId(), c.getName()))
                .toList();

        if (courses.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseReadDTO> getCourse(@PathVariable UUID id) {
        return service.findById(id)
                .map(c -> new CourseReadDTO(c.getId(), c.getName(), c.getDescription()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CourseReadDTO> createCourse(@RequestBody CourseDTO dto) {
        Course course = Course.builder()
                .id(UUID.randomUUID())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();

        Course saved = service.save(course);

        return ResponseEntity
                .created(URI.create("/api/courses/" + saved.getId()))
                .body(new CourseReadDTO(saved.getId(), saved.getName(), saved.getDescription()));

    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseReadDTO> updateCourse(@PathVariable UUID id, @RequestBody CourseDTO dto) {
        return service.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setDescription(dto.getDescription());
                    var updated = service.save(existing);
                    return ResponseEntity.ok(new CourseReadDTO(updated.getId(), updated.getName(), updated.getDescription()));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable UUID id) {
        if (!service.exists(id)) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
