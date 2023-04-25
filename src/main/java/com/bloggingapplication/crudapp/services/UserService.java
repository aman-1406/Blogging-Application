package com.bloggingapplication.crudapp.services;

import java.util.List;

import com.bloggingapplication.crudapp.payloads.UserDto;

public interface UserService {
	//Business logic is written in service class
	UserDto createUser(UserDto user);   // for creating user
	
	UserDto updateUser(UserDto user,Integer userId);	// for updating user
	
	UserDto getUserById(Integer userId);	//for finding particular used by id
	
	List<UserDto> getAllUsers();	// for finding all user details
	
	void deleteUser(Integer userId);  // for deleting particular user by userId
	
}