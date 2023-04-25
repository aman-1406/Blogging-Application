package com.bloggingapplication.crudapp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class JwtAuthRequest {

	private String username;
	
	private String password;
	
	
}
