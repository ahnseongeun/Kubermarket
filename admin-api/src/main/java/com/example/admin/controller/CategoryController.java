package com.example.admin.controller;

import com.example.admin.service.CategoryService;
import com.example.common.domain.Category;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping(value = "/api")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService=categoryService;
    }

    @ResponseBody
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    @ApiOperation(value = "Category Save(관리자 기능)", notes = "카테고리 저장")
    public ResponseEntity<?> create(
            @RequestParam(value="category", defaultValue = "false",required = false) String name
    ) throws URISyntaxException {
        Category category = categoryService.addCategory(name);
        String url = "/api/category/" + category.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @ResponseBody
    @RequestMapping(value = "/category/{id}",method = RequestMethod.PATCH)
    @ApiOperation(value = "Category Update(관리자 기능)", notes = "카테고리 수정")
    public Category update(@PathVariable("id") Long id,
                           @RequestParam(value="category", defaultValue = "false",required = false) String name
    ) throws URISyntaxException {
        return categoryService.updateCategory(id,name);
    }

    @ResponseBody
    @RequestMapping(value = "/category/{id}",method = RequestMethod.DELETE)
    @ApiOperation(value = "Category delete(관리자 기능)", notes = "카테고리 삭제")
    public String delete(
            @PathVariable("id") Long id
    ) throws URISyntaxException {
        categoryService.deleteCategory(id);
        return "{}";
    }

}
