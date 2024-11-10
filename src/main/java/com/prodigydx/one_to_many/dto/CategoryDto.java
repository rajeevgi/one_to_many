package com.prodigydx.one_to_many.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryDto {

    private int id;

    private String name;

    private List<ProductDto> products;

}
