package com.bloggingapplication.crudapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapplication.crudapp.Security.JwtTokenHelper;
import com.bloggingapplication.crudapp.entity.User;
import com.bloggingapplication.crudapp.payloads.JwtAuthRequest;
import com.bloggingapplication.crudapp.payloads.JwtAuthResponse;
import com.bloggingapplication.crudapp.payloads.UserDto;
import com.bloggingapplication.crudapp.services.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest request
			) throws Exception{
		
			this.authenticate(request.getUsername(),request.getPassword());		
		
		 UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
		
		 String token=this.jwtTokenHelper.generateToken(userDetails);
		 
		 JwtAuthResponse response=new JwtAuthResponse();
		 response.setToken(token);
		 
		 return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
		
	}
	
	
	

	private void authenticate(String username, String password) throws Exception {
		// here we created a authentication token
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
			
		// authentication manager will validate weather the user is valid or not
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (Exception e) {
			System.out.print("Invalid credentials");
			throw new Exception("Username or password  is invalid");
		}
	}

	

}
