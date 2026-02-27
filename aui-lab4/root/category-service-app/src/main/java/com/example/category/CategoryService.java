package com.example.category;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;
    private final ElementServiceClient event;

    public CategoryService(CategoryRepo categoryRepo,  ElementServiceClient event) {
        this.categoryRepo = categoryRepo;
        this.event = event;
    }

    public Category create(Category category){
         Category cat = categoryRepo.save(category);
         event.SendCategoryCreatedEvent(cat);
         return cat;
    }

    public void delete(Category category){
        Category cat = categoryRepo.findById(category.getId()).get();
        event.SendCategoryDeletedEvent(cat);
        categoryRepo.delete(cat);
    }

    public List<Category> findAll(){
        return categoryRepo.findAll();
    }

    public Category findById(Long id){
        return categoryRepo.findById(id).get();
    }
}
