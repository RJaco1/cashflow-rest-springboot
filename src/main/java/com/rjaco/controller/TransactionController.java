package com.rjaco.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rjaco.dto.TransactionReportDTO;
import com.rjaco.exception.ModelNotFoundException;
import com.rjaco.model.Transaction;
import com.rjaco.service.ITransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private ITransactionService service;

	// Gets all transactions income/expense
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Transaction>> pageable(Pageable pageable) {
		Page<Transaction> tran = service.pageable(pageable);
		return new ResponseEntity<Page<Transaction>>(tran, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Page<Transaction>> listByCatType(@PathVariable("id") Integer id, Pageable pageable) {
		Page<Transaction> tran = service.listByCatType(id, pageable);
		if (tran == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			return new ResponseEntity<Page<Transaction>>(tran, HttpStatus.OK);
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addTransaction(@Valid @RequestBody Transaction transaction) {
		Transaction tran = new Transaction();
		tran = service.createData(transaction);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(tran.getTransactionId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateAccount(@Valid @RequestBody Transaction transaction) {
		service.updateData(transaction);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeAccount(@PathVariable("id") Integer id) {
		Transaction tran = service.listDataUsingId(id);
		if (tran == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			service.deleteData(id);
		}
	}

	@GetMapping(value = "/transactionReport", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TransactionReportDTO>> transactionReport() {
		List<TransactionReportDTO> tran = new ArrayList<>();
		tran = service.listTransactionReport();
		return new ResponseEntity<List<TransactionReportDTO>>(tran, HttpStatus.OK);
	}

	@GetMapping(value = "/generateReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generateReport() {
		byte[] data = null;
		data = service.generateReport();
		if (data == null) {
			throw new ModelNotFoundException("data is null");
		} else {
			return new ResponseEntity<byte[]>(data, HttpStatus.OK);
		}
	}
}
