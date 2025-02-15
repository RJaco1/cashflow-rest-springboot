package com.rjaco.service;

import java.util.List;

import com.rjaco.dto.CategoryDTO;
import com.rjaco.model.Category;

public interface ICategoryService extends ICRUD<Category> {

    List<Category> findCategoriestByCatType(Integer categorytypeId);

    List<CategoryDTO> getDataDTO();

    List<CategoryDTO> findCategoriesByUsername(String username);

    List<CategoryDTO> findUserCategoriestByCatType(String username, Integer categorytypeId);

    CategoryDTO findUserCategory(String username, Integer id);

    Category createUserCategory(CategoryDTO categoryDTO);

    Category updateUserCategory(CategoryDTO categoryDTO);

    void deleteUserCategory(String username, Integer id);

}