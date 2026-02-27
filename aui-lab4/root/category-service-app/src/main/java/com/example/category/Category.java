package com.example.category;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Category parent;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}