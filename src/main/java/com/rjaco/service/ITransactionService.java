package com.rjaco.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rjaco.dto.TransactionReportDTO;
import com.rjaco.model.Transaction;

public interface ITransactionService extends ICRUD<Transaction> {

	Page<Transaction> listByCatType(Integer id, Pageable pageable);

	Page<Transaction> pageable(Pageable pageable);

	List<TransactionReportDTO> listTransactionReport();

	byte[] generateReport();
}
