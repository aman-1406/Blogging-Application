package com.bloggingapplication.crudapp.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	
	public static final String AUTHORIZATION_HEADER="Authorization";
	
	private ApiKey apiKeys() {
		return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securitySchemes(Arrays.asList(apiKeys()))
				.select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
				
	}

	private ApiInfo getInfo() {
		
		return new ApiInfo("Blogging appliication",
					"This project is developed by aman", 
					"1.0",
					"Terms of service",
					null,
					"Licenece of APIs",
					"API License url",Collections.emptyList());
	}
}
