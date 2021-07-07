package com.rjaco.service;

import java.util.List;

import com.rjaco.model.Category;

public interface ICategoryService extends ICRUD<Category> {

	List<Category> listByCatType(Integer categorytypeId);

}
