package com.rjaco.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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
import com.rjaco.model.CategoryType;
import com.rjaco.service.ICategoryTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/categoriesType")
@Api(value = "REST service for categories")
public class CategoryTypeController {

	@Autowired
	private ICategoryTypeService service;

	@ApiOperation("Returns a list of categories")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryType>> showList() {
		List<CategoryType> catType = new ArrayList<>();
		catType = service.listData();
		return new ResponseEntity<List<CategoryType>>(catType, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public EntityModel<CategoryType> showListById(@PathVariable("id") Integer id) {
		CategoryType catType = service.listDataUsingId(id);
		if (catType == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			EntityModel<CategoryType> resource = EntityModel.of(catType);
			Link linkTo = linkTo(methodOn(this.getClass()).showListById(id)).withRel("category-resource");
			resource.add(linkTo);
			return resource;
		}
	}

	// adding or creating category type
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addCategoryType(@Valid @RequestBody CategoryType cat) {
		CategoryType catType = new CategoryType();
		catType = service.createData(cat);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(catType.getCategorytypeId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCategoryType(@Valid @RequestBody CategoryType cat) {
		service.updateData(cat);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeCategoryType(@PathVariable("id") Integer id) {
		CategoryType catType = service.listDataUsingId(id);
		if (catType == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			service.deleteData(id);
		}
	}
}
