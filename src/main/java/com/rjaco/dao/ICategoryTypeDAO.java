package com.rjaco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rjaco.model.CategoryType;

public interface ICategoryTypeDAO extends JpaRepository<CategoryType, Integer> {

}
