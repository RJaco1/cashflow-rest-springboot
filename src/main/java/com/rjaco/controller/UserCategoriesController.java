package com.rjaco.controller;

import com.rjaco.dto.CategoryDTO;
import com.rjaco.model.Category;
import com.rjaco.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user-categories")
public class UserCategoriesController {

    @Autowired
    private ICategoryService service;

    @GetMapping(value = "/users/{username}/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDTO>> getUserCategories(@PathVariable("username") String username) {
        List<CategoryDTO> userCategories = service.findCategoriesByUsername(username);
        return new ResponseEntity<>(userCategories, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{username}/category-type/{id}/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDTO>> findUserCategoriesById(@PathVariable("username") String username, @PathVariable("id") Integer id) {
        List<CategoryDTO> userCategories = service.findUserCategoriestByCatType(username, id);
        return new ResponseEntity<>(userCategories, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{username}/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> findUserCategory(@PathVariable("username") String username, @PathVariable("id") Integer id) {
        CategoryDTO userCategories = service.findUserCategory(username, id);
        return new ResponseEntity<>(userCategories, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUserCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category cat = service.createUserCategory(categoryDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/users/{username}/categories/{id}")
                .buildAndExpand(cat.getUser().getUsername(), cat.getCategoryId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUserCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        service.updateUserCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{username}/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUserCategory(@PathVariable("username") String username, @PathVariable("id") Integer id) {
        service.deleteUserCategory(username, id);
    }
}