package com.example.admin.service;

import com.example.common.domain.Category;
import com.example.common.domain.CategoryRepository;
import com.example.common.domain.Jpa.CategoryRepositoryJpa;
import com.example.common.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class CategoryService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final CategoryRepositoryJpa categoryRepositoryjpa;

    @Autowired
    public CategoryService(RedisTemplate<String, Object> redisTemplate, CategoryRepositoryJpa categoryRepositoryjpa) {
        this.redisTemplate = redisTemplate;
        this.categoryRepositoryjpa = categoryRepositoryjpa;
    }

    public Category addCategory(String name) {
        Category category= Category.builder().name(name).build();
        categoryRepositoryjpa.save(category);
        String Key= "GetCategoryList";
        redisTemplate.delete(Key);
        return  category;
    }

    //@CacheEvict(key = "CategoryList",value = "Category",cacheManager = "CacheManager")
    public Category updateCategory(Long id,String name) {
        Category category = categoryRepositoryjpa.findById(id).orElse(null);
        category.setName(name);
        categoryRepositoryjpa.save(category);
        String Key= "GetCategoryList";
        redisTemplate.delete(Key);
        return  category;
    }

    //@CacheEvict(key = "CategoryList",value = "Category",cacheManager = "CacheManager")
    public void deleteCategory(Long id) {

        categoryRepositoryjpa.deleteById(id);
        String Key= "GetCategoryList";
        redisTemplate.delete(Key);
    }

    private CategoryDto convertEntityToDto(Category category) {
        CategoryDto categoryDto= new CategoryDto();
        return categoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

}
