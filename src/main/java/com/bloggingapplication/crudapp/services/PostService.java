package com.bloggingapplication.crudapp.services;

import java.util.List;

import com.bloggingapplication.crudapp.entity.Post;
import com.bloggingapplication.crudapp.payloads.PostDto;
import com.bloggingapplication.crudapp.payloads.PostResponse;

public interface PostService {
	
	//create 
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer PostId);
	
	//delete
	void deletePost(Integer PostId);
	
	//get single post 
	PostDto getPost(Integer postId);
	
	//getAll posts
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection);  
	
	// get all posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	// get all post by user
	List<PostDto> getPostsByUser(Integer userId);
	
	
	//search posts
	List<PostDto> searchPosts(String keyword);


}
