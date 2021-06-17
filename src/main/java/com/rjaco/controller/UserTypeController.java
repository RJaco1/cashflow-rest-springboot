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
import com.rjaco.model.UserType;
import com.rjaco.service.IUserTypeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usersType")
public class UserTypeController {
	
	@Autowired
	private IUserTypeService service;

	@ApiOperation("Returns a list of usTypeegories")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserType>> showList() {
		List<UserType> usType = new ArrayList<>();
		usType = service.listData();
		return new ResponseEntity<List<UserType>>(usType, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public EntityModel<UserType> showListById(@PathVariable("id") Integer id) {
		UserType usType = service.listDataUsingId(id);
		if (usType == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			EntityModel<UserType> resource = EntityModel.of(usType);
			Link linkTo = linkTo(methodOn(this.getClass()).showListById(id)).withRel("UserType-resource");
			resource.add(linkTo);
			//return new ResponseEntity<UserType>(usType, HttpStatus.OK);
			return resource;
		}
	}

	// adding or creating UserType type
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addUserType(@Valid @RequestBody UserType userType) {
		UserType usType = new UserType();
		usType = service.createData(userType);
		URI lousTypeion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(usType.getUserTypeId()).toUri();
		return ResponseEntity.created(lousTypeion).build();
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateUserType(@Valid @RequestBody UserType userType) {
		service.updateData(userType);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeUserType(@PathVariable("id") Integer id) {
		UserType usType = service.listDataUsingId(id);
		if (usType == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			service.deleteData(id);
		}
	}

}
