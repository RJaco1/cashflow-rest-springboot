package com.rjaco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rjaco.model.Currency;

public interface ICurrencyDAO extends JpaRepository<Currency, Integer>{

}
