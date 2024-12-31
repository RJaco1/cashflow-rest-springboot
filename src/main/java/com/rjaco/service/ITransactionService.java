package com.rjaco.service;

import java.util.List;

import com.rjaco.dto.CategoryDTO;
import com.rjaco.dto.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rjaco.dto.TransactionReportDTO;
import com.rjaco.model.Transaction;

public interface ITransactionService extends ICRUD<Transaction> {

	Page<Transaction> listByCatType(Integer id, Pageable pageable);

	Page<Transaction> pageable(Pageable pageable);

	List<TransactionDTO> listTranByUsername(String username);

	List<TransactionReportDTO> listTransactionReport();

	byte[] generateReport();
}
