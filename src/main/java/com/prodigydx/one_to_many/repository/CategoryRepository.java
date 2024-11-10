package com.prodigydx.one_to_many.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prodigydx.one_to_many.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
