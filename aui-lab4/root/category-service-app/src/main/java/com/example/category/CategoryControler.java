package com.example.category;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryControler {

    private final CategoryService categoryService;

    public CategoryControler(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category){
        return categoryService.create(category);
    }

    @GetMapping
    public List<Category> getAll(){
        return categoryService.findAll();
    }

    @DeleteMapping
    public void deleteCategory(@RequestBody Category category){
        categoryService.delete(category);
    }
}
