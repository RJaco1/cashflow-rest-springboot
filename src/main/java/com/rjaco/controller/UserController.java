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

import com.rjaco.dto.UsertypeUserDTO;
import com.rjaco.exception.ModelNotFoundException;
import com.rjaco.model.User;
import com.rjaco.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> showList() {
		List<User> us = new ArrayList<>();
		us = service.listData();
		return new ResponseEntity<List<User>>(us, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public EntityModel<User> showListById(@PathVariable("id") Integer id) {
		User us = service.listDataUsingId(id);
		if (us == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			EntityModel<User> resource = EntityModel.of(us);
			Link linkTo = linkTo(methodOn(this.getClass()).showListById(id)).withRel("category-resource");
			resource.add(linkTo);
			//return new ResponseEntity<CategoryType>(cat, HttpStatus.OK);
			return resource;
		}
	}
	
	@Transactional
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addUser(@Valid @RequestBody UsertypeUserDTO usertypeUserDTO) {
		User us = new User();
		us = service.createUser(usertypeUserDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(us.getUserId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateUser(@Valid @RequestBody User User) {
		service.updateData(User);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeUser(@PathVariable("id") Integer id) {
		User us = service.listDataUsingId(id);
		if (us == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			service.deleteData(id);
		}
	}
}
