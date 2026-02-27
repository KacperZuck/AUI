package com.example.element;


import jakarta.persistence.*;

@Entity
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private CategoryLite categoryLite;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
