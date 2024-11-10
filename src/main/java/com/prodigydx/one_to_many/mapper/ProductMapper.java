package com.prodigydx.one_to_many.mapper;

import com.prodigydx.one_to_many.dto.ProductDto;
import com.prodigydx.one_to_many.entity.Product;

public class ProductMapper {

    // Conversion of ProductDto to Product Object
    public static Product mappedProductDtoToProduct(ProductDto productDto, Product product){

        product.setProductId(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());

        return product;
    }

    // conversion of Product to ProductDto object
    public static ProductDto mappedProductToProductDto(Product product, ProductDto productDto){

        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getPrice());

        return productDto;
    }
}
