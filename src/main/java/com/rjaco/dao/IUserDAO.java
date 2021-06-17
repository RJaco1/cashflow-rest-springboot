package com.rjaco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rjaco.model.User;

public interface IUserDAO extends JpaRepository<User, Integer>{

}
