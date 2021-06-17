package com.rjaco.service;

import java.util.List;

import com.rjaco.model.Transaction;

public interface ITransactionService extends ICRUD<Transaction> {

	List<Transaction> transactionsList(Integer id);
}
