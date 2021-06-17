package com.rjaco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjaco.dao.ICategoryTypeDAO;
import com.rjaco.model.CategoryType;
import com.rjaco.service.ICategoryTypeService;

@Service
public class CategoryTypeServiceImpl implements ICategoryTypeService {

	@Autowired
	private ICategoryTypeDAO dao;

	@Override
	public CategoryType createData(CategoryType t) {
		return dao.save(t);
	}

	@Override
	public CategoryType updateData(CategoryType t) {
		return dao.save(t);
	}

	@Override
	public void deleteData(int id) {
		dao.deleteById(id);
	}

	@Override
	public CategoryType listDataUsingId(int id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public List<CategoryType> listData() {
		return dao.findAll();
	}

}
