package com.rjaco.dto;

import com.rjaco.model.CategoryType;

public class CategoryDTO {
    private int categoryId;
    private String categoryName;
    private CategoryType categorytype;
    private int userId;
    private String username;
    private String email;

    public CategoryDTO() {
    }

    public CategoryDTO(int categoryId, String categoryName, CategoryType categorytype, int userId, String username, String email) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categorytype = categorytype;
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryType getCategorytype() {
        return categorytype;
    }

    public void setCategorytype(CategoryType categorytype) {
        this.categorytype = categorytype;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
