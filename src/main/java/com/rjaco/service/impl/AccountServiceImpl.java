package com.rjaco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjaco.dao.IAccountDAO;
import com.rjaco.model.Account;
import com.rjaco.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{
	
	@Autowired
	private IAccountDAO dao;

	@Override
	public Account createData(Account t) {
		return dao.save(t);
	}

	@Override
	public Account updateData(Account t) {
		return dao.save(t);
	}

	@Override
	public void deleteData(int id) {
		dao.deleteById(id);
	}

	@Override
	public Account listDataUsingId(int id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public List<Account> listData() {
		return dao.findAll();
	}

}
