package com.user.app.service;

import java.util.List;

import com.user.app.exception.UserNotFoundException;
import com.user.app.model.User;

public interface IUserService {
	
	public User findUserByUserName(String username) throws UserNotFoundException;
	
	public User createUser(User user);
	
	public void deleteUserById(Long id); 
	
	public List<User> getAllUsersList();

}
