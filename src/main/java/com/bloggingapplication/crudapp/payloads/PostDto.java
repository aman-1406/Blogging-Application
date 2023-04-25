package com.bloggingapplication.crudapp.payloads;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bloggingapplication.crudapp.entity.Category;
import com.bloggingapplication.crudapp.entity.Comment;
import com.bloggingapplication.crudapp.entity.User;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
	
	private Integer postId;

	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
//	private String addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	
	// advantage of this line is when we fetch the posts, all comments will also go to them.
	private Set<CommentDto> comments=new HashSet<>();
	
	
	
	
	
	
/*	
	@NotBlank
	private String imgaeName="default.png";
	
	@NotBlank
	private Date addedDate;
	
	@NotBlank
	private Category category;
	
	@NotBlank
	private User user;
*/

}
