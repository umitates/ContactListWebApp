package com.umitates.cl.core.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.umitates.cl.db.entity.UserEntity;
import com.umitates.cl.db.repository.UserRepository;

@Component
public class LoginUserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity getAuthenticatedUserEntity() {
		User user = getAuthenticatedUser();
		return findByUsername(user.getUsername());
	}

	@Cacheable (value = "user", key = "#username")
	private UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User getAuthenticatedUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
