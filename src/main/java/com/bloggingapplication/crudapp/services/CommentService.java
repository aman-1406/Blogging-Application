package com.bloggingapplication.crudapp.services;

import java.util.List;

import com.bloggingapplication.crudapp.payloads.CommentDto;
import com.bloggingapplication.crudapp.payloads.PostDto;

public interface CommentService {
	
	// create comment on a post
	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	
	// get single comment by given commentId
	CommentDto getComment(Integer commentId);
	
	// get all comments
	List<CommentDto> getAllComments();
	
	
	// delete comment
	void deleteComment(Integer commentId);
	
	// get comment by user
//	List<CommentDto> getCommentsByUser(Integer userId);

	

}
