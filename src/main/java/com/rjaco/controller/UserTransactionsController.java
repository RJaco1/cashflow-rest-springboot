package com.rjaco.controller;

import com.rjaco.dto.TransactionDTO;
import com.rjaco.dto.TransactionReportDTO;
import com.rjaco.model.Transaction;
import com.rjaco.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user-transactions")
public class UserTransactionsController {

    @Autowired
    private ITransactionService service;

    @GetMapping(value = "/users/{username}/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<TransactionDTO>> getUserTransactions(@PathVariable("username") String username, Pageable pageable) {
        Page<TransactionDTO> userTransactions = service.findTransactionsByUsername(username, pageable);
        return new ResponseEntity<>(userTransactions, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{username}/category-type/{id}/transactions")
    public ResponseEntity<Page<TransactionDTO>> findUserTransactionsById(@PathVariable("username") String username, @PathVariable("id") Integer id, Pageable pageable) {
        Page<TransactionDTO> userTransactions = service.findUserTransactionsByCatType(username, id, pageable);
        return new ResponseEntity<>(userTransactions, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{username}/transactions/{id}")
    public ResponseEntity<TransactionDTO> findUserTransactionById(@PathVariable("username") String username, @PathVariable("id") Integer id) {
        TransactionDTO userTransaction = service.findUserTransactionById(username, id);
        return new ResponseEntity<>(userTransaction, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUserTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = service.createUserTransaction(transactionDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/users/{username}/transactions/{id}")
                .buildAndExpand(transaction.getUser().getUsername(), transaction.getTransactionId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUserTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        service.updateUserTransaction(transactionDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{username}/transactions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUserTransaction(@PathVariable("username") String username, @PathVariable("id") Integer id) {
        service.deleteUserTransaction(username, id);
    }

    @GetMapping(value = "users/{username}/transactions-report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionReportDTO>> userTransactionsReport(@PathVariable("username") String username) {
        List<TransactionReportDTO> tran = service.findUserTransactionsReport(username);
        return new ResponseEntity<List<TransactionReportDTO>>(tran, HttpStatus.OK);
    }
}
