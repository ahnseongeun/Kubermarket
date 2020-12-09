package com.example.common.domain.Jpa;

import com.example.common.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryJpa {

    List<Category> findAll();

    void save(Category category);

    Optional<Category> findById(Long id);

    void deleteById(Long id);

    Optional<Category> findByName(String name);
}
