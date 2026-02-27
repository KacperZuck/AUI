package com.example.element;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elements")
public class ElementController {

    private final ElementService service;

    public ElementController(ElementService service) {
        this.service = service;
    }

    @PostMapping
    public Element create(@RequestBody Element element) {
        return service.create(element);
    }

    @GetMapping
    public List<Element> getAll() {
        return service.findAll();
    }
}

