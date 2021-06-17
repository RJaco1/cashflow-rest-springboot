package com.rjaco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjaco.dao.ITransactionDAO;
import com.rjaco.model.Transaction;
import com.rjaco.service.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService {

	@Autowired
	private ITransactionDAO dao;

	@Override
	public Transaction createData(Transaction t) {
		return dao.save(t);
	}

	@Override
	public Transaction updateData(Transaction t) {
		return dao.save(t);
	}

	@Override
	public void deleteData(int id) {
		dao.deleteById(id);
	}

	@Override
	public Transaction listDataUsingId(int id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public List<Transaction> listData() {
		return dao.findAll();
	}

	@Override
	public List<Transaction> transactionsList(Integer id) {
		return dao.transactionsList(id);
	}

	

}
