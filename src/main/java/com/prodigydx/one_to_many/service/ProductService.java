package com.prodigydx.one_to_many.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodigydx.one_to_many.dto.ProductDto;
import com.prodigydx.one_to_many.entity.Category;
import com.prodigydx.one_to_many.entity.Product;
import com.prodigydx.one_to_many.exception.ResourceNotFoundException;
import com.prodigydx.one_to_many.mapper.ProductMapper;
import com.prodigydx.one_to_many.repository.CategoryRepository;
import com.prodigydx.one_to_many.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Post mapping to save product
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = ProductMapper.mappedProductDtoToProduct(productDto, new Product());

        productRepository.save(product);

        productDto.setProductId(product.getProductId());

        return productDto;
    }

    // Get Mapping to get product by Id
    public ProductDto getProductById(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found!"));

        return ProductMapper.mappedProductToProductDto(product, new ProductDto());
    }

    // Get Mapping to list all the products
    public List<ProductDto> getAllProducts() {
        
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product : products){
            ProductDto productDto = ProductMapper.mappedProductToProductDto(product, new ProductDto());
            productDtos.add(productDto);
        }

        return productDtos;
    }

    // Put mapping to update Product details
    public String updateProduct(int updateId, ProductDto productDto) {
        
        Product product = productRepository.findById(updateId).orElseThrow(() -> new ResourceNotFoundException("Product not found with Id: "+updateId));

        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());

        productRepository.save(product);

        return "Product with ID: "+updateId+" updated successfully...";
    }

    // Delete mapping to remove product details by id
    public String deleteProductById(int deleteId) {
        
        Product product = productRepository.findById(deleteId).orElseThrow(() -> new ResourceNotFoundException("Product not found with Id: "+deleteId));

        productRepository.delete(product);

        return " Product with Id: "+deleteId+" deleted successfully....";
    }

    /* Mappings for Category-Product(One to many Relationship) */
    
    // Adding Product to Category
    public ProductDto addProductToCategory(int categoryId, int productId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found."));

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found."));

        product.setCategory(category);

        product = productRepository.save(product);

        return ProductMapper.mappedProductToProductDto(product, new ProductDto());
    }

    // Removing Product from Category
    public ProductDto removeProductFromCategory(int categoryId, int productId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found."));

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found."));

        if(!product.getCategory().equals(category)){
            throw new ResourceNotFoundException("Product is not associated with the given category.");
        }

        product.setCategory(null);

        // product.setProductId(null);

        product = productRepository.save(product);

        return ProductMapper.mappedProductToProductDto(product, new ProductDto());

    }

}
