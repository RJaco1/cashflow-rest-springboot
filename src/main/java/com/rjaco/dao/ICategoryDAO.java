package com.rjaco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rjaco.model.Category;

public interface ICategoryDAO extends JpaRepository<Category, Integer>{

}
