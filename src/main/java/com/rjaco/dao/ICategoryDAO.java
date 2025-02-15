package com.rjaco.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rjaco.model.Category;

public interface ICategoryDAO extends JpaRepository<Category, Integer>{
	
	@Query("FROM Category c WHERE c.categorytype.categorytypeId = :categorytypeId")
	List<Category> findCategoriesByCatType(@Param("categorytypeId") Integer categorytypeId);

	@Query("FROM Category c WHERE c.user.userId = :userId")
	List<Category> findCategoriesByUserId(@Param("userId") Integer userId);

	@Query("FROM Category c WHERE c.user.userId = :userId AND c.categorytype.categorytypeId = :categorytypeId")
	List<Category> findUserCategoriestByCatType(@Param("userId") Integer userId, @Param("categorytypeId") Integer categorytypeId);

	@Query("FROM Category c WHERE c.user.userId = :userId AND c.categoryId = :categoryId")
	Category findUserCategory(@Param("userId") Integer userId, @Param("categoryId") Integer categoryId);

}
