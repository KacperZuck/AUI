package com.example.element;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementService {

    private final ElementRepo elementRepository;
    private final CategoryLiteRepo categoryRepository;

    public ElementService(ElementRepo elementRepository, CategoryLiteRepo categoryRepository) {
        this.elementRepository = elementRepository;
        this.categoryRepository = categoryRepository;
    }

    public Element create(Element element) {
        return elementRepository.save(element);
    }

    public List<Element> findAll() {
        return elementRepository.findAll();
    }

    public void  delete(Long id) {
        elementRepository.deleteById(id);
    }
}
