package com.rjaco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjaco.dao.ICategoryDAO;
import com.rjaco.dao.ICategorytypeCategoryDAO;
import com.rjaco.dto.CategorytypeCategoryDTO;
import com.rjaco.model.Category;
import com.rjaco.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryDAO dao;

	@Autowired
	private ICategorytypeCategoryDAO catTypeDao;

	@Override
	public Category createData(Category t) {
		return dao.save(t);
	}

	@Override
	public Category updateData(Category t) {
		return dao.save(t);
	}

	@Override
	public void deleteData(int id) {
		dao.deleteById(id);
	}

	@Override
	public Category listDataUsingId(int id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public List<Category> listData() {
		return dao.findAll();
	}

	@Override
	public Category createData(CategorytypeCategoryDTO categoryDTO) {
		dao.save(categoryDTO.getCategory());
		catTypeDao.addCategory(categoryDTO.getCategory().getCategoryId(), categoryDTO.getCategorytype().getCategoryTypeId());
		return categoryDTO.getCategory();
	}

}
