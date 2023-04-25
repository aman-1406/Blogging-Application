package com.bloggingapplication.crudapp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bloggingapplication.crudapp.entity.User;
import com.bloggingapplication.crudapp.exception.ResourceNotFoundException;
import com.bloggingapplication.crudapp.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading  user from database by username,here we have considered username as email
	User user=	this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email : "+username, 0));

	return user;
	}

}
