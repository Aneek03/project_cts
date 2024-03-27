package com.aneek.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aneek.book.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
