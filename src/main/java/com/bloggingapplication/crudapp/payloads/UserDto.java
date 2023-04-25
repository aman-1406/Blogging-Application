package com.bloggingapplication.crudapp.payloads;


import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min=4,message = "User name must be greater than 4 characters !!")
	private String name;
	
	@Email(message = "Email is not valid!!")
	private String email;
	
	@NotEmpty
	@Size(min=4,max=10,message="Password must be between 4 and 10 characters !!")
//	@Pattern(regexp="Alfkewn@1334")
	private String password;
	
	@NotEmpty
	private String about;
	
	// advantage of this line is user get all of his comments
	private Set<CommentDto> comments= new HashSet<>();

}
