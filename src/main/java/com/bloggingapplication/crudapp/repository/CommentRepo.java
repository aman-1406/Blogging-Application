package com.bloggingapplication.crudapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapplication.crudapp.entity.Comment;
import com.bloggingapplication.crudapp.entity.User;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

//	List<Comment> findById(User user);

}
