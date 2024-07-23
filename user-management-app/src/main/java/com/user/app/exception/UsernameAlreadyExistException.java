package com.user.app.exception;

public class UsernameAlreadyExistException extends RuntimeException{
	
	public UsernameAlreadyExistException(String username) {
		super("User already exists with username: "+username);
	}

}
