package com.example.element;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ElementRepo extends JpaRepository<Element,Long> {

    void deleteById(@Param("id") Long id);
}
