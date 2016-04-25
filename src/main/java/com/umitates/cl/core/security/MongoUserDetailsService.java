package com.umitates.cl.core.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.umitates.cl.db.entity.UserEntity;
import com.umitates.cl.db.repository.UserRepository;

/**
 * Components takes part in user authentication using mongodb users
 * @author Umit Ates
 */
@Component
public class MongoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Validate authenticated user
	 * @param username unique identifier for user
	 * @return authenticated user details
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		UserEntity userEntity = getUserDetail(username);

		User user = new User(userEntity.getUsername(),
				userEntity.getPassword(), enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked,
				getAuthorities(userEntity.getRole()));
		
		return user;
	}

	private List<SimpleGrantedAuthority> getAuthorities(String role) {
		return Arrays.asList(new SimpleGrantedAuthority(role));
	}

	private UserEntity getUserDetail(String username) {
		UserEntity user = userRepository.findByUsername(username);
		return user;
	}

}