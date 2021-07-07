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
import com.rjaco.model.Currency;
import com.rjaco.service.ICurrencyService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
	
	@Autowired
	private ICurrencyService service;

	@ApiOperation("Returns a list of curegories")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Currency>> showList() {
		List<Currency> cur = new ArrayList<>();
		cur = service.listData();
		return new ResponseEntity<List<Currency>>(cur, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public EntityModel<Currency> showListById(@PathVariable("id") Integer id) {
		Currency cur = service.listDataUsingId(id);
		if (cur == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			EntityModel<Currency> resource = EntityModel.of(cur);
			Link linkTo = linkTo(methodOn(this.getClass()).showListById(id)).withRel("Currency-resource");
			resource.add(linkTo);
			return resource;
		}
	}

	// adding or creating Currency type
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addCurrency(@Valid @RequestBody Currency Currency) {
		Currency cur = new Currency();
		cur = service.createData(Currency);
		URI locurion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cur.getCurrencyId()).toUri();
		return ResponseEntity.created(locurion).build();
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCurrency(@Valid @RequestBody Currency cur) {
		service.updateData(cur);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeCurrency(@PathVariable("id") Integer id) {
		Currency cur = service.listDataUsingId(id);
		if (cur == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			service.deleteData(id);
		}
	}
}
