package com.bloggingapplication.crudapp.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bloggingapplication.crudapp.payloads.*;
import com.bloggingapplication.crudapp.services.UserService;
import com.bloggingapplication.crudapp.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	// POST -> for creating new user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto){
		UserDto createUserDto=this.userService.createUser(userdto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	
	// PUT -> to update details
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userdto,@PathVariable Integer userId){
		UserDto updatedUser=this.userService.updateUser(userdto, userId);
		return ResponseEntity.ok(updatedUser);
	}
	
	// Role based authentication -> only Admin can hit this api (delete the user from blog application)
	// DELETE -> deleting user details
	@DeleteMapping("/{userid}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted sucessfully",true),HttpStatus.OK);
	}
	
	// GET -> get all the user details
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	// GET -> this get will return user detail of single user with given userId
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	

}











