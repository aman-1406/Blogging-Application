package com.bloggingapplication.crudapp.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bloggingapplication.crudapp.entity.Category;
import com.bloggingapplication.crudapp.entity.Post;
import com.bloggingapplication.crudapp.entity.User;
import com.bloggingapplication.crudapp.exception.ResourceNotFoundException;
import com.bloggingapplication.crudapp.payloads.PostDto;
import com.bloggingapplication.crudapp.payloads.PostResponse;
import com.bloggingapplication.crudapp.repository.CategoryRepo;
import com.bloggingapplication.crudapp.repository.PostRepo;
import com.bloggingapplication.crudapp.repository.UserRepo;
import com.bloggingapplication.crudapp.services.PostService;


import net.bytebuddy.description.ModifierReviewable.OfAbstraction;


@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categroyRepo;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		// find user
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
		
		// find category
		Category category=this.categroyRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		
		// only title and content come through DTO so we have to set other entities explicitly		
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		Date date = new Date();
		System.out.println(date);
		post.setAddedDate(date);
		post.setUser(user);
		post.setCategory(category);
		
		
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		//find user
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost=this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post pos=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
		this.postRepo.delete(pos);
	}

	@Override
	public PostDto getPost(Integer postId) {
		// TODO Auto-generated method stub
		Post pos=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		
		return this.modelMapper.map(pos, PostDto.class);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection) {

		Sort sort=null;
		
		if(sortDirection.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		
		//creating the object of page and take input as pageNo and pageSize as parameter which is passed through PostService
		Pageable p=PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		
		//(pageNumber, pageSize, Sort.by(sortBy));
		// Take 1 page at a time
		Page<Post> pagePost=this.postRepo.findAll(p);
		
		// Take the list of pages and pass it to Dto
		List<Post> allPosts = pagePost.getContent();
		
		List<PostDto> postDtos = allPosts.stream().map((post)->this.modelMapper.map(allPosts, PostDto.class)).collect(Collectors.toList());
		
		
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPage(pagePost.getNumber());
		
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category cat=	this.categroyRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
		
		List<Post> posts= this.postRepo.findByCategory(cat);
		
		// all post will be converted into list in postDtos and returned
		List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		// finding user if present 
		User userr=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user id", userId));
		
		// finding posts posted by user
		List<Post> posts=this.postRepo.findByUser(userr);
		
		// all posts will be converted into list of PostDtos and returned
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts=this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos=	posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
