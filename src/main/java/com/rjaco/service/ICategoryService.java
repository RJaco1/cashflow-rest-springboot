package com.rjaco.service;

import com.rjaco.dto.CategorytypeCategoryDTO;
import com.rjaco.model.Category;

public interface ICategoryService extends ICRUD<Category> {
	
	Category createData(CategorytypeCategoryDTO categoryDTO);

}
