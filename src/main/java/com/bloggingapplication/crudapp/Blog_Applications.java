package com.bloggingapplication.crudapp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Blog_Applications implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Blog_Applications.class, args);
	}
	
	//Here we create a bean for modelmapper
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
	
	// for password encoding
	@Override
	public void run(String... args) throws Exception {
		System.out.print(this.passwordEncoder.encode("helloworld"));
		
	}
	

}

/*
 * Amazon EC2
 * Amazon Elastic Beanstalk
 * Amazon RDS  -> SQL
 * Amazon Route 53
 * Amazon S3
 * 
 * 
 * 
 * */
