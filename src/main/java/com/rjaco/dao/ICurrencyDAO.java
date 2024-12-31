package com.rjaco.dao;

import com.rjaco.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rjaco.model.Currency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICurrencyDAO extends JpaRepository<Currency, Integer>{

    @Query("FROM Currency a WHERE a.user.userId = :userId")
    List<Currency> listCurrByUserId(@Param("userId") Integer userId);

}
