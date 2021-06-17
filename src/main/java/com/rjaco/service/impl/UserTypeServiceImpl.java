package com.rjaco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjaco.dao.IUserTypeDAO;
import com.rjaco.model.UserType;
import com.rjaco.service.IUserTypeService;

@Service
public class UserTypeServiceImpl implements IUserTypeService{

	@Autowired
	private IUserTypeDAO dao;
	
	@Override
	public UserType createData(UserType t) {
		return dao.save(t);
	}

	@Override
	public UserType updateData(UserType t) {
		return dao.save(t);
	}

	@Override
	public void deleteData(int id) {
		dao.deleteById(id);
	}

	@Override
	public UserType listDataUsingId(int id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public List<UserType> listData() {
		return dao.findAll();
	}

}
