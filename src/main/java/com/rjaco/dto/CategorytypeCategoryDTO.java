package com.rjaco.dto;

import com.rjaco.model.Category;
import com.rjaco.model.CategoryType;

public class CategorytypeCategoryDTO {
	
	private CategoryType categorytype;
	private Category category;
	
	public CategoryType getCategorytype() {
		return categorytype;
	}
	public void setCategorytype(CategoryType categorytype) {
		this.categorytype = categorytype;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
