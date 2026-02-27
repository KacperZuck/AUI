package com.example.category;

import com.example.element.CategoryLite;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ElementServiceClient {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:8082/elements/";

    public ElementServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public void SendCategoryCreatedEvent(Category category) {
        CategoryEventDto to = new CategoryEventDto(category.getId(), category.getName());
        restTemplate.postForObject(BASE_URL + "categoryCreated", to, Void.class);
    }

    public void SendCategoryDeletedEvent(Category category) {
        restTemplate.delete(BASE_URL + "categoryDeleted/" + category.getId());
    }
}
