package com.rjaco.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rjaco.model.Transaction;

public interface ITransactionDAO extends JpaRepository<Transaction, Integer> {
	
	@Query("FROM Transaction t JOIN Category c ON t.category.categoryId = c.categoryId "
			+ "JOIN CategorytypeCategory ct ON c.categoryId = ct.category.categoryId "
			+ "JOIN CategoryType ctype ON ct.categoryType.categoryTypeId = ctype.categoryTypeId "
			+ "WHERE ctype.categoryTypeId = :categorytypeid")
	List<Transaction> transactionsList(@Param("categorytypeid") Integer categorytypeid);

}
