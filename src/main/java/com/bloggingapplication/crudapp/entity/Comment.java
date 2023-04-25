	package com.bloggingapplication.crudapp.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comments")
@Getter
@Setter
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;
	
	
	private String content;
	
	// on which post the user is commenting
	@ManyToOne
	private Post post;
	
	// which user is commenting on the post
	@ManyToOne
	private User user;
	
	
	

}
