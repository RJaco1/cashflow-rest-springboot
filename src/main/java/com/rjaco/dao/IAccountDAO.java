package com.rjaco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rjaco.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAccountDAO extends JpaRepository<Account, Integer> {

    @Query("FROM Account a WHERE a.user.userId = :userId")
    List<Account> findAccountsByUserId(@Param("userId") Integer userId);

}
