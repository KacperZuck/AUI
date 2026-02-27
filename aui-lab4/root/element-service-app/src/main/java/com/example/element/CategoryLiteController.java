package com.example.element;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/local-categories")
public class CategoryLiteController {

    private final CategoryLiteService service;

    public CategoryLiteController(CategoryLiteService service) {
        this.service = service;
    }

    @PostMapping
    public CategoryLite create(@RequestBody CategoryLite category) {
        return service.create(category);
    }

    @GetMapping
    public List<CategoryLite> getAll() {
        return service.findAll();
    }

    @PostMapping
    public void delete(@RequestBody CategoryLite category) {
        service.delete(category.getId());
    }
}

