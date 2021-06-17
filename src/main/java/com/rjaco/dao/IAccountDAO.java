package com.rjaco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rjaco.model.Account;

public interface IAccountDAO extends JpaRepository<Account, Integer>{

}
