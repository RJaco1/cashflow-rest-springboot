package com.rjaco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjaco.dao.IUserDAO;
import com.rjaco.dao.IUsertypeUserDAO;
import com.rjaco.dto.UsertypeUserDTO;
import com.rjaco.model.User;
import com.rjaco.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO dao;
	
	@Autowired
	private IUsertypeUserDAO uDao;
	
	@Override
	public User createData(User t) {
		return dao.save(t);
	}

	@Override
	public User updateData(User t) {
		return dao.save(t);
	}

	@Override
	public void deleteData(int id) {
		dao.deleteById(id);		
	}

	@Override
	public User listDataUsingId(int id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public List<User> listData() {
		return dao.findAll();
	}

	@Override
	public User createUser(UsertypeUserDTO usertypeUserDTO) {
		dao.save(usertypeUserDTO.getUser());
		uDao.addUser(usertypeUserDTO.getUser().getUserId(), usertypeUserDTO.getUserType().getUserTypeId());
		return usertypeUserDTO.getUser();
	}

}
