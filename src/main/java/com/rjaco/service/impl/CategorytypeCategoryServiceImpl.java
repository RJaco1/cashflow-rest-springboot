package com.rjaco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjaco.dao.ICategorytypeCategoryDAO;
import com.rjaco.model.CategorytypeCategory;
import com.rjaco.service.ICategorytypeCategoryService;

@Service
public class CategorytypeCategoryServiceImpl implements ICategorytypeCategoryService{
	
	@Autowired
	private ICategorytypeCategoryDAO dao;

	@Override
	public List<CategorytypeCategory> findCatAndType() {
		return dao.findCatAndType();
	}

}
