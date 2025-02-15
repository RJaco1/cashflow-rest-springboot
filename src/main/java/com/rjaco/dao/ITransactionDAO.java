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
    Page<Transaction> findByCatType(@Param("categorytypeId") Integer categorytypeId, Pageable pageable);

    @Query("FROM Transaction t WHERE t.user.userId = :userId AND t.category.categorytype.categorytypeId = :categorytypeId")
    Page<Transaction> findUserTransactionsByCatType(@Param("userId") Integer userId, @Param("categorytypeId") Integer categorytypeId, Pageable pageable);

    @Query("FROM Transaction a WHERE a.user.userId = :userId")
    Page<Transaction> findTranByUserId(@Param("userId") Integer userId, Pageable pageable);

    @Query("FROM Transaction t WHERE t.user.userId = :userId AND t.transactionId = :transactionId")
    Transaction findUserTransactionById(@Param("userId") Integer userId, @Param("transactionId") Integer transactionId);

	/* @Query("FROM Transaction a WHERE a.user.userId = :userId")
	Page<Transaction> findTranByUserId(@Param("userId") Integer userId, Pageable pageable); */

    @Query(value = "select expense, sum(income - expense) OVER(ORDER BY date\r\n"
            + "     ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) as income, date from\r\n"
            + "(select sum(case when (ct.categorytype_id = 1)\r\n"
            + "		   then t.amount else 0 end) as income,\r\n"
            + "		   sum(case when (ct.categorytype_id = 2)\r\n"
            + "		   then t.amount else 0 end) as expense, to_char(t.date, 'yyyy/MM/dd') as date from transaction t\r\n"
            + "INNER JOIN category c on t.category_id = c.category_id\r\n"
            + "INNER JOIN category_type ct on c.categorytype_id = ct.categorytype_id\r\n"
            + "group by t.date order by t.date) as transactions_report;", nativeQuery = true)
    List<Object[]> transactionsReport();

    @Query(value = "select expense, sum(income - expense) OVER(ORDER BY date\r\n"
            + "     ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) as income, date from\r\n"
            + "(select sum(case when (ct.category_type = 'Income')\r\n"
            + "		   then t.amount else 0 end) as income,\r\n"
            + "		   sum(case when (ct.category_type = 'Expenses')\r\n"
            + "		   then t.amount else 0 end) as expense, to_char(t.transaction_date, 'yyyy/MM/dd') as date from transactions t\r\n"
            + "INNER JOIN categories c on t.category_id = c.category_id\r\n"
            + "INNER JOIN categories_type ct on c.categorytype_id = ct.categorytype_id\r\n"
            + "WHERE t.user_id = :userId\r\n"
            + "group by t.transaction_date order by t.transaction_date) as transactions_report;", nativeQuery = true)
    List<Object[]> userTransactionsReport(@Param("userId") Integer userId);

}
