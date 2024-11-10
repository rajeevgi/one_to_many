package com.prodigydx.one_to_many.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prodigydx.one_to_many.dto.CategoryDto;
import com.prodigydx.one_to_many.entity.Category;
import com.prodigydx.one_to_many.exception.ResourceNotFoundException;
import com.prodigydx.one_to_many.mapper.CategoryMapper;
import com.prodigydx.one_to_many.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Post/Save mapping to insert category
    public CategoryDto saveCategory(CategoryDto categoryDto) {

        Category category = CategoryMapper.mappedCategoryDtoToCategory(categoryDto, new Category());
        
        categoryRepository.save(category);

        categoryDto.setId(category.getId());

        return categoryDto;
        
    }

    // Get Mapping to get category by id
    public CategoryDto getCategoryById(int id) {
        
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found!"));

        return CategoryMapper.mappedCategoryToCategoryDto(category, new CategoryDto());
        
    }

    // Get mapping to list all the categories
    public List<CategoryDto> getAllCategories() {
        
        List<Category> categories = categoryRepository.findAll();

        List<CategoryDto> categoryDtos = new ArrayList<>();

        for(Category category : categories){
            CategoryDto categoryDto = CategoryMapper.mappedCategoryToCategoryDto(category, new CategoryDto());
            categoryDtos.add(categoryDto);
        }

        return categoryDtos;
    }

    // Put mapping to update category
    public String updateCategoryById(int updateId, CategoryDto categoryDto) {
        
        Category category = categoryRepository.findById(updateId).orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: "+updateId));

        category.setName(categoryDto.getName());

        categoryRepository.save(category);

        return "Category updated Successfully....";
    }

    // Delete mapping to remove category
    public String deleteCategoryById(int deleteId) {
        Category category = categoryRepository.findById(deleteId).orElseThrow(() -> new ResourceNotFoundException("Category Id: "+deleteId+" not found"));

        categoryRepository.delete(category);

        return "Category with Id:"+deleteId+" deleted Successfully...";
    }

}
