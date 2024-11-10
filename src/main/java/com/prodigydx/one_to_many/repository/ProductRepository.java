package com.prodigydx.one_to_many.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prodigydx.one_to_many.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
