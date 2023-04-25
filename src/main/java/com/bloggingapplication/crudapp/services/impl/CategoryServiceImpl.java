package com.bloggingapplication.crudapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingapplication.crudapp.entity.Category;
import com.bloggingapplication.crudapp.exception.ResourceNotFoundException;
import com.bloggingapplication.crudapp.payloads.CategoryDto;
import com.bloggingapplication.crudapp.repository.CategoryRepo;
import com.bloggingapplication.crudapp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
			Category cat=this.modelMapper.map(categoryDto, Category.class);
			Category addedCat=this.categoryRepo.save(cat);
		
			return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer catogeryId) {
			Category cat=this.categoryRepo.findById(catogeryId)
					.orElseThrow(()->new ResourceNotFoundException("Category", "Category Id",catogeryId));
			
			cat.setCategoryTitle(categoryDto.getCategoryTitle());
			cat.setCategoryDescription(categoryDto.getCategoryDescription());
			
			Category updatedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCat,CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category  cat= 	this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		
		// as return type is category dto so we cannot return category directly 
		// we will convert category into category dto and then return categorydto

		List<CategoryDto> catDtos =	categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());

		return catDtos;
	}

}
