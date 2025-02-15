package com.rjaco.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.rjaco.dto.CurrencyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rjaco.exception.ModelNotFoundException;
import com.rjaco.model.Currency;
import com.rjaco.service.ICurrencyService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/currencies")
public class CurrenciesController {
	
	@Autowired
	private ICurrencyService service;

	@ApiOperation("Returns a list of curegories")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CurrencyDTO>> getCurrencies(@RequestParam(required = false) String username) {
		List<CurrencyDTO> cur = username == null ? service.getDataDTO() : service.findCurrenciesByUsername(username);
		return new ResponseEntity<>(cur, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public EntityModel<Currency> findCurrencyById(@PathVariable("id") Integer id) {
		Currency cur = service.findData(id);
		if (cur == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			EntityModel<Currency> resource = EntityModel.of(cur);
			Link linkTo = linkTo(methodOn(this.getClass()).findCurrencyById(id)).withRel("Currency-resource");
			resource.add(linkTo);
			return resource;
		}
	}

	// adding or creating Currency type
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createCurrency(@Valid @RequestBody Currency Currency) {
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
		Currency cur = service.findData(id);
		if (cur == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			service.deleteData(id);
		}
	}
}
