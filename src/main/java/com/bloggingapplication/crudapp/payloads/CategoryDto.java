package com.bloggingapplication.crudapp.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
		
	private Integer categoryId;
	
	@NotBlank
	private String categoryTitle;
	
	@NotBlank
	private String categoryDescription;
}
