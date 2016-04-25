package com.umitates.cl.core.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.umitates.cl.db.entity.UserEntity;
import com.umitates.cl.db.repository.UserRepository;

/**
 * Components takes part in authenticated user retrieval
 * @author Umit Ates
 */
@Component
public class LoginUserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Method retrieves authenticated user information from session context and load from entity context
	 * @return authenticated user entity
	 */
	public UserEntity getAuthenticatedUserEntity() {
		User user = getAuthenticatedUser();
		return findByUsername(user.getUsername());
	}

	public User getAuthenticatedUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	/**
	 * Method retrieves authenticated user entity from entity context
	 * @param username unique identifier for user
	 * @return authenticated user entity
	 */
	@Cacheable (value = "user", key = "#username")
	private UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
