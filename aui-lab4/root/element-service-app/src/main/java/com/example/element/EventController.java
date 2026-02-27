package com.example.element;

import com.example.category.CategoryEventDto;
import com.example.category.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class EventController {

    private final CategoryLiteService categoryService;
    private final ElementService elementService;

    public EventController(CategoryLiteService categoryService, ElementService elementService) {
        this.categoryService = categoryService;
        this.elementService = elementService;
    }

    @PostMapping("/CateoryCreated")
    public void createCategory (@RequestBody CategoryEventDto event ){
        CategoryLite lite = new CategoryLite(event.getId(), event.getName());
        lite.setCategoryName(event.getName());
        lite.setId( event.getId());
        categoryService.create(lite);
    }

    @PostMapping("/CategoryDelete/{id}")
    public void deleteCategory(@PathVariable Long id ){
        elementService.delete(id);
        categoryService.delete(id);
    }
}
