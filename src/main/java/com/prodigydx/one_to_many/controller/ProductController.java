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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prodigydx.one_to_many.dto.ProductDto;
import com.prodigydx.one_to_many.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/saveProduct")
    public ProductDto saveProduct(@RequestBody ProductDto productDto){
        return productService.saveProduct(productDto);
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable int productId){
        return productService.getProductById(productId);
    }

    @GetMapping("/getAllProduct")
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/{updateId}")
    public String updateProduct(@RequestBody ProductDto productDto, @PathVariable int updateId){
        return productService.updateProduct(updateId, productDto);
    }

    @DeleteMapping("/{deleteId}")
    public String deleteProduct(@PathVariable int deleteId){
        return productService.deleteProductById(deleteId);
    }

    // All the category-product(one to many relationship) mappings

    @GetMapping("/addProductToCategory")
    public ProductDto addProductToCategory(@RequestParam int categoryId, @RequestParam int productId){
        return productService.addProductToCategory(categoryId, productId);
    }

    @PutMapping("/remove")
    public ProductDto removeProductFromCategory(@RequestParam int categoryId, @RequestParam int productId){
        return productService.removeProductFromCategory(categoryId, productId);
    }
}
