package com.rjaco.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.rjaco.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rjaco.exception.ModelNotFoundException;
import com.rjaco.model.Account;
import com.rjaco.service.IAccountService;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

	@Autowired
	private IAccountService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AccountDTO>> getAccounts(@RequestParam(required = false) String username) {
		List<AccountDTO> acc = username == null ? service.getDataDTO() : service.findAccuntsByUsername(username);
		return new ResponseEntity<>(acc, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public EntityModel<Account> findAccountById(@PathVariable("id") Integer id) {
		Account acc = service.findData(id);
		if (acc == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			EntityModel<Account> resource = EntityModel.of(acc);
			Link linkTo = linkTo(methodOn(this.getClass()).findAccountById(id)).withRel("category-resource");
			resource.add(linkTo);
			return resource;
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createAccount(@Valid @RequestBody Account account) {
		Account acc = new Account();
		acc = service.createData(account);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(acc.getAccountId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateAccount(@Valid @RequestBody Account account) {
		service.updateData(account);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeAccount(@PathVariable("id") Integer id) {
		Account acc = service.findData(id);
		if (acc == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			service.deleteData(id);
		}
	}
	
}
