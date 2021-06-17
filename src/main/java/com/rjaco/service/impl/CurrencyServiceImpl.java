package com.rjaco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjaco.dao.ICurrencyDAO;
import com.rjaco.model.Currency;
import com.rjaco.service.ICurrencyService;

@Service
public class CurrencyServiceImpl implements ICurrencyService{

	@Autowired
	private ICurrencyDAO dao;
	
	@Override
	public Currency createData(Currency t) {
		return dao.save(t);
	}

	@Override
	public Currency updateData(Currency t) {
		return dao.save(t);
	}

	@Override
	public void deleteData(int id) {
		dao.deleteById(id);		
	}

	@Override
	public Currency listDataUsingId(int id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public List<Currency> listData() {
		return dao.findAll();
	}

}
