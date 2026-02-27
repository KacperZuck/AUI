package com.example.element;

import com.example.category.CategoryRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class CategoryLiteService {

    private final CategoryLiteRepo categoryLiteRepo;


    public CategoryLiteService(CategoryLiteRepo categoryLiteRepo) {
        this.categoryLiteRepo = categoryLiteRepo;
    }

    public void delete(Long id){
        categoryLiteRepo.deleteById(id);
    }

    public List<CategoryLite> findAll() {
        return categoryLiteRepo.findAll();
    }

    public CategoryLite create(CategoryLite category) {
        return categoryLiteRepo.save(category);
    }


}
