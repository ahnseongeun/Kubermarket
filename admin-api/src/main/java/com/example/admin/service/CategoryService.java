package com.example.admin.service;

import com.example.common.domain.Category;
import com.example.common.domain.CategoryRepository;
import com.example.common.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final CategoryRepository categoryRepository;

    public Category addCategory(String name) {
        Category category= Category.builder().name(name).build();
        categoryRepository.save(category);
        String Key= "GetCategoryList";
        redisTemplate.delete(Key);
        return  category;
    }

    //@CacheEvict(key = "CategoryList",value = "Category",cacheManager = "CacheManager")
    public Category updateCategory(Long id,String name) {
        Category category = categoryRepository.findById(id).orElse(null);
        category.setName(name);
        categoryRepository.save(category);
        String Key= "GetCategoryList";
        redisTemplate.delete(Key);
        return  category;
    }

    //@CacheEvict(key = "CategoryList",value = "Category",cacheManager = "CacheManager")
    public void deleteCategory(Long id) {

        categoryRepository.deleteById(id);
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
