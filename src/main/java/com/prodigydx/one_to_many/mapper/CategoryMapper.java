package com.prodigydx.one_to_many.mapper;
import java.util.ArrayList;
import java.util.List;

import com.prodigydx.one_to_many.dto.CategoryDto;
import com.prodigydx.one_to_many.dto.ProductDto;
import com.prodigydx.one_to_many.entity.Category;
import com.prodigydx.one_to_many.entity.Product;

public class CategoryMapper {

    // conversion of categoryDto to category object
    public static Category mappedCategoryDtoToCategory(CategoryDto categoryDto, Category category){
        
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());

        return category;
    }

    // conversion of category to categoryDto
    public static CategoryDto mappedCategoryToCategoryDto(Category category, CategoryDto categoryDto){

        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        
        // Convert the List<Product> to List<ProductDto> 
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product : category.getProducts()){
            ProductDto productDto = new ProductDto();

            productDto.setProductId(product.getProductId());
            productDto.setProductName(product.getProductName());
            productDto.setPrice(product.getPrice());

            productDtos.add(productDto);
        }
        categoryDto.setProducts(productDtos);

        return categoryDto;
    }
}
