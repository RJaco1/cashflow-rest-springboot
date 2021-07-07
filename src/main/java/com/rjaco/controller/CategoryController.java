package com.rjaco.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
import com.rjaco.model.Category;
import com.rjaco.service.ICategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private ICategoryService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> findCatAndType() {
		List<Category> cat = new ArrayList<>();
		cat = service.listData();
		return new ResponseEntity<List<Category>>(cat, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<List<Category>> listByCatType(@PathVariable("id") Integer id) {
		List<Category> cat = new ArrayList<>();
		cat = service.listByCatType(id);
		if (cat == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			return new ResponseEntity<List<Category>>(cat, HttpStatus.OK);
		}
	}

	@Transactional
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addCategory(@Valid @RequestBody Category category) {
		Category cat = new Category();
		cat = service.createData(category);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cat.getCategoryId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@Transactional
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCategory(@Valid @RequestBody Category category) {
		service.updateData(category);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeCategory(@PathVariable("id") Integer id) {
		Category cat = service.listDataUsingId(id);
		if (cat == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			service.deleteData(id);
		}
	}

}
