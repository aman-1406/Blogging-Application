package com.bloggingapplication.crudapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingapplication.crudapp.entity.User;
import com.bloggingapplication.crudapp.payloads.UserDto;
import com.bloggingapplication.crudapp.repository.UserRepo;
import com.bloggingapplication.crudapp.services.UserService;
import com.bloggingapplication.crudapp.exception.*;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		userRepo.save(user);
		User  savedUser=userRepo.findByName(user.getName());
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		
		// fetched the user by id
		User user=this.userRepo.findById(userId).orElseThrow(()->  new ResourceNotFoundException("User","id",userId));
		
		// updated the details of user
		user.setId(userdto.getId());
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		
		// saved the details of user
		User updatedUser= this.userRepo.save(user);
		
		// passed the user to dto so that it can update it into the database.
		UserDto userDto1=this.userToDto(updatedUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		// return the user to dto so dto can search the field with given id
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		this.userRepo.delete(user);

	}

	// passing data from dto to user
	/*
	private User  dtoToUser(UserDto userDto) {
			User user=new User();
			user.setId(userDto.getId());
			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setPassword(userDto.getPassword());
			user.setAbout(userDto.getAbout());
			
			return user;
	}
	*/
	// Instead of manually converting , we use modelMapper
	private User  dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	
	//passing data from user to dto
	/*
	public UserDto userToDto(User user) {
		UserDto userdto=new UserDto();
		userdto.setId(user.getId());
		userdto.setName(user.getName());
		userdto.setEmail(user.getEmail());
		userdto.setPassword(user.getPassword());
		userdto.setAbout(user.getAbout());
		
		return  userdto;
	}
	*/
	
	// Similarly we also use modelMapper here
	public UserDto userToDto(User user) {
		UserDto userdto=this.modelMapper.map(user, UserDto.class);
		return userdto;
	}
}
