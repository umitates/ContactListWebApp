package com.umitates.cl.contact;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.umitates.cl.db.entity.ContactEntity;
import com.umitates.cl.db.entity.UserEntity;
import com.umitates.cl.db.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/spring/spring-servlet.xml")
public class TestQueryContactController {

	private MockMvc mockMvc;
	
	@InjectMocks
    private QueryContactController queryContactController;
	 
	@Mock
    private UserRepository userRepository;
 
    @Autowired
    private WebApplicationContext webApplicationContext;
 
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    	initAuthenticatedUser();
        initContactController();
    }

    @Test
    public void showQueryContactPageToListLoginUserContacts() throws Exception {
    	ContactEntity contact = new ContactEntity();
    	
    	UserEntity userEntity = new UserEntity();
    	userEntity.setUsername("umit");
    	userEntity.setContacts(Arrays.asList(contact));
    	when(userRepository.findByUsername("umit")).thenReturn(userEntity);

    	
		mockMvc.perform(get("/contact/query"))
                .andExpect(status().isOk())
                .andExpect(view().name("/contact/contact_query"))
                .andExpect(forwardedUrl("/contact/contact_query"))
                .andExpect(model().attributeExists("contacts"));
		
		verify(userRepository, times(1)).findByUsername("umit");
        verifyZeroInteractions(userRepository);
    }

    private void initContactController() {
		mockMvc = MockMvcBuilders.standaloneSetup(queryContactController).build();
	}

	private void initAuthenticatedUser() {
		Collection<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    	User user = new User("umit", "1234", authorities);
		Authentication authentication = new TestingAuthenticationToken(user, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}