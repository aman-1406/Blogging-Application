package com.bloggingapplication.crudapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapplication.crudapp.entity.Category;
import com.bloggingapplication.crudapp.entity.Post;
import com.bloggingapplication.crudapp.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	// custom finder method
	// here we can explicitly create different types of method to finduser,findcategory etc so that we can find it using jparepository
	List<Post> findByUser(User user);	// find all posts by particular user
	List<Post> findByCategory(Category category);	// find all categories by particular user
	
	List<Post> findByTitleContaining(String keyword);	// find the given keyword in the title string 

}
