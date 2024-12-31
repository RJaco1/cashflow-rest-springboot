package com.rjaco.service;

import java.util.List;

import com.rjaco.dto.CategoryDTO;
import com.rjaco.model.Category;
import org.springframework.data.repository.query.Param;

public interface ICategoryService extends ICRUD<Category> {

	List<Category> listByCatType(Integer categorytypeId);

	List<CategoryDTO> listCatByUsername(String username);

}
