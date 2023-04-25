package com.bloggingapplication.crudapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bloggingapplication.crudapp.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	@Query(value="SELECT * FROM USER WHERE name=?1", nativeQuery=true)
	User findByName(String name);
	
	// this is used for finding the email id from user entity and will be used in CustomUserDetailService for authentication purpose
	Optional<User> findByEmail(String email);

}
