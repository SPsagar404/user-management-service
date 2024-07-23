package com.user.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.app.exception.UserNotFoundException;
import com.user.app.exception.UsernameAlreadyExistException;
import com.user.app.model.Role;
import com.user.app.model.User;
import com.user.app.repository.IUserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostConstruct
    public void init() {
        // Create an admin user if not exists
        if (repository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(Role.ROLE_ADMIN);
            repository.save(admin);
        }
    }


	@Override
	public User findUserByUserName(String username) throws UserNotFoundException {
		Optional<User> user = repository.findByUsername(username);
		if(user.isEmpty()) {
			throw new UserNotFoundException("User not exists with username: "+username);
		}
		return user.get();
	}

	@Override
	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_USER);
		Optional<User> existingUser = repository.findByUsername(user.getUsername());
		if(existingUser.isPresent()) {
			throw new UsernameAlreadyExistException(user.getUsername());
		}
		return repository.save(user);
	}

	@Override
	public void deleteUserById(Long id) {
		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("User not found with userId : "+id);
		}
		repository.deleteById(id);
	}
	
	@Override
	public List<User> getAllUsersList() {
		List<User> users = repository.findAll();
		if(users.isEmpty()) {
			throw new UserNotFoundException();
		}
		return users;
	}
	
	
	

}
