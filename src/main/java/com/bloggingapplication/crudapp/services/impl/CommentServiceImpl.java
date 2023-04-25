package com.bloggingapplication.crudapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingapplication.crudapp.entity.Comment;
import com.bloggingapplication.crudapp.entity.Post;
import com.bloggingapplication.crudapp.entity.User;
import com.bloggingapplication.crudapp.exception.ResourceNotFoundException;
import com.bloggingapplication.crudapp.payloads.CommentDto;
import com.bloggingapplication.crudapp.repository.CommentRepo;
import com.bloggingapplication.crudapp.repository.PostRepo;
import com.bloggingapplication.crudapp.repository.UserRepo;
import com.bloggingapplication.crudapp.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		// find if the given post exists or not 
		 Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		 
		 Comment comment=this.modelMapper.map(commentDto, Comment.class);
		 // set the comment inside the post
		 comment.setPost(post);
		 
		 Comment savedComment=this.commentRepo.save(comment);
		 
		return this.modelMapper.map(savedComment, CommentDto.class);
	}	
	
	@Override
	public CommentDto getComment(Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));
		return this.modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public List<CommentDto> getAllComments() {
		List<Comment> comments=this.commentRepo.findAll();
		List<CommentDto> commentDto=comments.stream().map(comment->this.commentToDto(comment)).collect(Collectors.toList());
		return commentDto;
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comm= this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));
		this.commentRepo.delete(comm);
	}
	

	/*
	@Override
	public List<CommentDto> getCommentsByUser(Integer userId) {
		// find user if it exists
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user id", userId));
		
		// find all the comments for particular user
		List<Comment> comments=this.commentRepo.findById(user);
				
		// all comments will be converted into list of commentDtos and returned
		List<CommentDto> commentDtos=comments.stream().map((comment)->this.modelMapper.map(comment,CommentDto.class).collect(Collectors.toList()));
				
			return commentDtos;
	}
	
	*/
	
	// this is used to convert comment into dto
	public CommentDto commentToDto(Comment comment) {
			CommentDto commentDto=this.modelMapper.map(comment, CommentDto.class);
			return commentDto;
	}





}
