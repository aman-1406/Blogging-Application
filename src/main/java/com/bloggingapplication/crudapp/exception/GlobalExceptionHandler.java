package com.bloggingapplication.crudapp.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.bloggingapplication.crudapp.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/*
	 *  GlobalExceptionHandler is a class used to handle exception in the
	 * entire application, whenever an error occur it will be redirected here.
	 * In this class all type of error logic are written this class is used
	 * to handle ResourceNotFoundException exception.Whenever the project
	 * find any error "ResourceNotFoundException" then it will handle the error 
	 * ApiResponse -> this class is already implemented
	 * ResourceNotFoundException -> this is also already implemented
	 */	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException temp){
		String message =temp.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		// blank map to store error message
		Map<String,String> responses=new HashMap<>();
		
		// Now we extract error from ex and put it into map in the form  of (field,Value) pair
		// let say if error occurred in "name", then we extract message which is written in top of name in userDto       
		
        
		// here we are iterating the error message which is occurred 
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName= ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            responses.put(fieldName, message);
        });	
		return new ResponseEntity<Map<String,String>>(responses,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CredentialsNotValidException.class)
	public ResponseEntity<ApiResponse> credentialsNotValidException(CredentialsNotValidException e){
		
			
			String message=e.getMessage();
			ApiResponse apiResponse=new ApiResponse(message,false);

		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.UNAUTHORIZED);
	}

}
