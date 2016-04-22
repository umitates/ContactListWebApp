package com.umitates.cl.core.session;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.umitates.cl.db.entity.UserEntity;
import com.umitates.cl.db.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/spring/spring-servlet.xml")
public class TestLoginUserDetailsService {
	
	@InjectMocks
	private LoginUserDetailsService loginUserDetailsService;

	@Mock
    private UserRepository userRepository;
	
	@Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    	initAuthenticatedUser();
    }
	
	@Test
	public void componentReturnsLoginUserFromSessionContext() {
		
		User user = loginUserDetailsService.getAuthenticatedUser();
		
		Assert.assertEquals("umit", user.getUsername());
		Assert.assertEquals("1234", user.getPassword());
	}
	
	public void componentReturnsLoginUserFromDataBase() {
		when(userRepository.findByUsername("umit")).thenReturn(new UserEntity());
		
		Assert.assertNotNull(loginUserDetailsService.getAuthenticatedUserEntity());
	}
	
	private void initAuthenticatedUser() {
		Collection<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    	User user = new User("umit", "1234", authorities);
		Authentication authentication = new TestingAuthenticationToken(user, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
