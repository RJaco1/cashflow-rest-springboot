package com.rjaco.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rjaco.model.Transaction;

public interface ITransactionDAO extends JpaRepository<Transaction, Integer> {

	@Query("FROM Transaction t WHERE t.category.categorytype.categorytypeId = :categorytypeId")
	Page<Transaction> listByCatType(@Param("categorytypeId") Integer categorytypeId, Pageable pageable);

	@Query(value = "select expense, sum(income - expense) OVER(ORDER BY date\r\n"
			+ "     ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) as income, date from\r\n"
			+ "(select sum(case when (ct.categorytype_id = 1)\r\n"
			+ "		   then t.amount else 0 end) as income,\r\n"
			+ "		   sum(case when (ct.categorytype_id = 2)\r\n"
			+ "		   then t.amount else 0 end) as expense, to_char(t.date, 'yyyy/MM/dd') as date from transaction t\r\n"
			+ "INNER JOIN category c on t.category_id = c.category_id\r\n"
			+ "INNER JOIN category_type ct on c.categorytype_id = ct.categorytype_id\r\n"
			+ "group by t.date order by t.date) as transactions_report;", nativeQuery = true)
	List<Object[]> listTransactionReport();

}
