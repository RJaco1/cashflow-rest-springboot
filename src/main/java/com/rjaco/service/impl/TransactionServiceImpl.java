package com.rjaco.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rjaco.dao.ITransactionDAO;
import com.rjaco.dto.TransactionReportDTO;
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
	public Page<Transaction> listByCatType(Integer id, Pageable pageable) {
		return dao.listByCatType(id, pageable);
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
	public Page<Transaction> pageable(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public List<TransactionReportDTO> listTransactionReport() {
		List<TransactionReportDTO> t = new ArrayList<>();
		dao.listTransactionReport().forEach(x -> {
			TransactionReportDTO tDTO = new TransactionReportDTO();
			tDTO.setExpense(Double.parseDouble(String.valueOf(x[0])));
			tDTO.setIncome(Double.parseDouble(String.valueOf(x[1])));
			tDTO.setDate(String.valueOf(x[2]));
			t.add(tDTO);
		});
		return t;
	}

}
