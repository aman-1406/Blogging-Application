package com.bloggingapplication.crudapp.exception;

import org.springframework.validation.BindException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsNotValidException extends RuntimeException{

	String resourceName;
	String fieldName;
	String fieldValue;
	
	public CredentialsNotValidException(String resourceName,String fieldName, String fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldValue));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}
	
}
