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
    public Category listDataUsingId(int id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<Category> listData() {
        return dao.findAll();
    }

    @Override
    public List<Category> listByCatType(Integer categorytypeId) {
        return dao.listByCatType(categorytypeId);
    }

    @Override
    public List<CategoryDTO> listCatByUsername(String username) {

        UserAccount user = userDAO.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", username));
        }

        List<CategoryDTO> catDto = new ArrayList<>();
        dao.listCatByUserId(user.getUserId()).forEach(category -> {
            catDto.add(new CategoryDTO(
                    category.getCategoryId(),
                    category.getCategoryName(),
                    category.getCategorytype(),
                    category.getUser().getUserId(),
                    category.getUser().getUsername(),
                    category.getUser().getEmail())
            );
        });
        return catDto;
    }
}
