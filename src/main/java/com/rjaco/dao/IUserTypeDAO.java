package com.rjaco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rjaco.model.UserType;

public interface IUserTypeDAO extends JpaRepository<UserType, Integer>{

}
