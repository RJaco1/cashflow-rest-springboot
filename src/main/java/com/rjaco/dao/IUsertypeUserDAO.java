package com.rjaco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rjaco.model.UsertypeUser;

public interface IUsertypeUserDAO extends JpaRepository<UsertypeUser, Integer> {

	@Modifying
	@Query(value = "INSERT INTO usertype_user(user_id, usertype_id) VALUES (:userId, :usertypeId)", nativeQuery = true)
	Integer addUser(@Param("userId") Integer userId, @Param("usertypeId") Integer usertypeId);
}
