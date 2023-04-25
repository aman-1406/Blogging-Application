package com.bloggingapplication.crudapp.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapplication.crudapp.payloads.ApiResponse;
import com.bloggingapplication.crudapp.payloads.CommentDto;
import com.bloggingapplication.crudapp.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	// CREATE COMMENT on the post
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
		
		CommentDto createCommentDto=this.commentService.createComment(commentDto, postId);
			
		return new ResponseEntity<>(createCommentDto,HttpStatus.CREATED);
	}
	
	
	// DELETE COMMENT on the post
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		
		this.commentService.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted sucessfully",true),HttpStatus.OK);
	}
	
	
	// GET COMMENT
	@GetMapping("/comments/{commentId}")
	public ResponseEntity<CommentDto> getComment(@PathVariable Integer commentId) {
		return ResponseEntity.ok(this.commentService.getComment(commentId)) ;
	}
	
	
	// GET ALL COMMENTS
	@GetMapping("/comments")
	public ResponseEntity<List<CommentDto>> getAllComments(){
		return ResponseEntity.ok(this.commentService.getAllComments());
	}
	
	

}
