package com.rjaco.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.rjaco.dao.IUserAccountDAO;
import com.rjaco.dto.CategoryDTO;
import com.rjaco.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rjaco.dao.ICategoryDAO;
import com.rjaco.model.Category;
import com.rjaco.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDAO dao;

    @Autowired
    private IUserAccountDAO userDAO;

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
    public Category findData(int id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<Category> getData() {
        return dao.findAll();
    }

    @Override
    public List<Category> findCategoriestByCatType(Integer categorytypeId) {
        return dao.findCategoriesByCatType(categorytypeId);
    }

    @Override
    public List<CategoryDTO> getDataDTO() {
        List<CategoryDTO> catDto = new ArrayList<>();
        dao.findAll().forEach(category -> {
            catDto.add(new CategoryDTO(
                    category.getCategoryId(),
                    category.getCategoryName(),
                    category.getUser().getUserId(),
                    category.getUser().getUsername(),
                    category.getUser().getEmail(),
                    category.getCategorytype())
            );
        });
        return catDto;
    }

    @Override
    public List<CategoryDTO> findCategoriesByUsername(String username) {

        UserAccount user = userDAO.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", username));
        }

        List<CategoryDTO> catDto = new ArrayList<>();
        dao.findCategoriesByUserId(user.getUserId()).forEach(category -> {
            catDto.add(new CategoryDTO(
                    category.getCategoryId(),
                    category.getCategoryName(),
                    category.getUser().getUserId(),
                    category.getUser().getUsername(),
                    category.getUser().getEmail(),
                    category.getCategorytype())
            );
        });
        return catDto;
    }

    @Override
    public List<CategoryDTO> findUserCategoriestByCatType(String username, Integer categorytypeId) {

        UserAccount user = userDAO.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", username));
        }
        List<CategoryDTO> catDto = new ArrayList<>();
        dao.findUserCategoriestByCatType(user.getUserId(), categorytypeId).forEach(category -> {
            catDto.add(new CategoryDTO(
                    category.getCategoryId(),
                    category.getCategoryName(),
                    category.getUser().getUserId(),
                    category.getUser().getUsername(),
                    category.getUser().getEmail(),
                    category.getCategorytype())
            );
        });

        return catDto;
    }

    @Override
    public CategoryDTO findUserCategory(String username, Integer id) {

        UserAccount user = userDAO.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", username));
        }

        Category cat = dao.findUserCategory(user.getUserId(), id);

        return new CategoryDTO(
                cat.getCategoryId(),
                cat.getCategoryName(),
                cat.getUser().getUserId(),
                cat.getUser().getUsername(),
                cat.getUser().getEmail(),
                cat.getCategorytype());
    }

    @Override
    public Category createUserCategory(CategoryDTO categoryDTO) {
        UserAccount user = userDAO.findOneByUsername(categoryDTO.getUsername());

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", categoryDTO.getUsername()));
        }

        Category cat = new Category();
        cat.setCategoryName(categoryDTO.getCategoryName());
        cat.setCategorytype(categoryDTO.getCategorytype());
        cat.setUser(user);

        return dao.save(cat);
    }

    @Override
    public Category updateUserCategory(CategoryDTO categoryDTO) {
        UserAccount user = userDAO.findOneByUsername(categoryDTO.getUsername());

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", categoryDTO.getUsername()));
        }
        Category cat = new Category();
        cat.setCategoryId(categoryDTO.getCategoryId());
        cat.setCategoryName(categoryDTO.getCategoryName());
        cat.setCategorytype(categoryDTO.getCategorytype());
        cat.setUser(user);

        return dao.save(cat);
    }

    @Override
    public void deleteUserCategory(String username, Integer id) {
        UserAccount user = userDAO.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", username));
        }

        dao.deleteById(id);
    }
}
