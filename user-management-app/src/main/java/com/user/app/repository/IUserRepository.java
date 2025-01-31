package com.user.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.app.model.User;

public interface IUserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
	
	Long deleteByUsername(String username);
	
}
