package com.prodigydx.one_to_many.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodigydx.one_to_many.dto.CategoryDto;
import com.prodigydx.one_to_many.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/saveCategory")
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.saveCategory(categoryDto);
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable int id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/getAllCategories")
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PutMapping("/updateCategory/{updateId}")
    public String updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable int updateId){
        return categoryService.updateCategoryById(updateId, categoryDto);
    }

    @DeleteMapping("/deleteCategory/{deleteId}")
    public String deleteCategory(@PathVariable int deleteId){
        return categoryService.deleteCategoryById(deleteId);
    }
}
