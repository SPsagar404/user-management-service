package com.user.app.service;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.app.model.User;
import com.user.app.repository.IUserRepository;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private IUserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByUsername(username).get();
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),Arrays.asList(user.getRole()).stream().map(role->new SimpleGrantedAuthority(role.name())).collect(Collectors.toList()));
	}

}
