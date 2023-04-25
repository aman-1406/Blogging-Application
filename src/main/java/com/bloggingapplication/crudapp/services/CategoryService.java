package com.bloggingapplication.crudapp.services;

import java.util.List;
import com.bloggingapplication.crudapp.payloads.CategoryDto;

public interface CategoryService {

	//create 
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer catogeryId);
	
	//delete
	void deleteCategory(Integer categoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	//get all
	List<CategoryDto> getCategories();
}
