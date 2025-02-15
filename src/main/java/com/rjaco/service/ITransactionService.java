package com.rjaco.service;

import java.util.List;

import com.rjaco.dto.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rjaco.dto.TransactionReportDTO;
import com.rjaco.model.Transaction;

public interface ITransactionService extends ICRUD<Transaction> {

    Page<Transaction> findByCategoryType(Integer id, Pageable pageable);

    Page<Transaction> pageable(Pageable pageable);

    Page<TransactionDTO> getDataDTO(Pageable pageable);

    Page<TransactionDTO> findTransactionsByUsername(String username, Pageable pageable);

    Page<TransactionDTO> findUserTransactionsByCatType(String username, Integer id, Pageable pageable);

    TransactionDTO findUserTransactionById(String username, Integer id);

    Transaction createUserTransaction(TransactionDTO transactionDTO);

    Transaction updateUserTransaction(TransactionDTO transactionDTO);

    void deleteUserTransaction(String username, Integer id);

    List<TransactionReportDTO> listTransactionReport();

    List<TransactionReportDTO> findUserTransactionsReport(String username);

    byte[] generateReport();
}
