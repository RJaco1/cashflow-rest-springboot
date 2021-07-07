package com.rjaco.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rjaco.model.Category;

public interface ICategoryDAO extends JpaRepository<Category, Integer>{
	
	@Query("FROM Category c WHERE c.categorytype.categorytypeId = :categorytypeId")
	List<Category> listByCatType(@Param("categorytypeId") Integer categorytypeId);

}
