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

import com.rjaco.dto.CategorytypeCategoryDTO;
import com.rjaco.exception.ModelNotFoundException;
import com.rjaco.model.Category;
import com.rjaco.model.CategorytypeCategory;
import com.rjaco.service.ICategoryService;
import com.rjaco.service.ICategorytypeCategoryService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private ICategoryService service;
	
	@Autowired
	private ICategorytypeCategoryService typeService;

	@ApiOperation("Returns a list of categories")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> showList() {
		List<Category> cat = new ArrayList<>();
		cat = service.listData();
		return new ResponseEntity<List<Category>>(cat, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public EntityModel<Category> showListById(@PathVariable("id") Integer id) {
		Category cat = service.listDataUsingId(id);
		if (cat == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			EntityModel<Category> resource = EntityModel.of(cat);
			Link linkTo = linkTo(methodOn(this.getClass()).showListById(id)).withRel("category-resource");
			resource.add(linkTo);
			//return new ResponseEntity<Category>(cat, HttpStatus.OK);
			return resource;
		}
	}

	@Transactional
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addCategory(@Valid @RequestBody CategorytypeCategoryDTO categoryDTO) {
		Category cat = new Category();
		cat = service.createData(categoryDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cat.getCategoryId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCategory(@Valid @RequestBody Category cat) {
		service.updateData(cat);
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
	
	@GetMapping(value = "/categorie")
	public ResponseEntity<List<CategorytypeCategory>> findCatAndType(){
		List<CategorytypeCategory> caterorytypeCat = new ArrayList<>();
		caterorytypeCat = typeService.findCatAndType();
		return new ResponseEntity<List<CategorytypeCategory>>(caterorytypeCat, HttpStatus.OK);
	}

}
