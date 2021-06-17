package com.rjaco.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
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

import com.rjaco.exception.ModelNotFoundException;
import com.rjaco.model.Transaction;
import com.rjaco.service.ITransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private ITransactionService service;

	//Gets all transactions income/expense
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Transaction>> showList() {
		List<Transaction> tran = new ArrayList<>();
		tran = service.listData();
		return new ResponseEntity<List<Transaction>>(tran, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public EntityModel<Transaction> showListById(@PathVariable("id") Integer id) {
		Transaction tran = new Transaction();
		tran = service.listDataUsingId(id);
		if (tran == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			EntityModel<Transaction> resource = EntityModel.of(tran);
			Link linkTo = linkTo(methodOn(this.getClass()).showListById(id)).withRel("Transaction-Resource");
			resource.add(linkTo);
			return resource;
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
		Transaction acc = service.listDataUsingId(id);
		if (acc == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			service.deleteData(id);
		}
	}

	//"id" filters transaction by income or expense - 1 = income / 2 = expense
	@GetMapping(value = "list/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Transaction>> showTransactionList(@PathVariable("id") Integer id) {
		List<Transaction> tran = new ArrayList<>();
		tran = service.transactionsList(id);
		return new ResponseEntity<List<Transaction>>(tran, HttpStatus.OK);
	}
}
