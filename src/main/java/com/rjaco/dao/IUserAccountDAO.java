package com.rjaco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rjaco.model.UserAccount;

public interface IUserAccountDAO extends JpaRepository<UserAccount, Integer> {

    UserAccount findOneByUsername(String username);

}
