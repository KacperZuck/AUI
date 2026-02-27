package com.example.element;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryLiteRepo  extends JpaRepository<CategoryLite,Long> {
}
