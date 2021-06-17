package com.rjaco.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rjaco.model.CategorytypeCategory;

public interface ICategorytypeCategoryDAO extends JpaRepository<CategorytypeCategory, Integer> {

	@Modifying
	@Query(value = "INSERT INTO categorytype_category(category_id, categorytype_id) VALUES (:categoryId, :categorytypeId)", nativeQuery = true)
	Integer addCategory(@Param("categoryId") Integer categoryId, @Param("categorytypeId") Integer categorytypeId);
	
	
	//@Query("from CategorytypeCategory ct where ct.categorytype.categorytypeId = :categorytypeId")
	@Query("from CategorytypeCategory")
	List<CategorytypeCategory> findCatAndType();
}
