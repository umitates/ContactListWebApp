package com.umitates.cl.contact;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
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
public class TestDeleteContactController  {

	private MockMvc mockMvc;
	
	@InjectMocks
    private RemoveContactController removeContactController;
	 
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
    public void submitRemoveContact() throws Exception {
    	ContactEntity contact = new ContactEntity();
    	contact.setId("9876");
    
    	UserEntity userEntity = new UserEntity();
    	userEntity.setUsername("umit");
    	userEntity.setContacts(new ArrayList<ContactEntity>());
		userEntity.getContacts().add(contact);
    	
    	when(userRepository.findByUsername("umit")).thenReturn(userEntity);
    	when(userRepository.save(userEntity)).thenReturn(userEntity);
    	
		mockMvc.perform(get("/contact/remove/9876"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/contact/query"));
		

		verify(userRepository, times(1)).findByUsername("umit");
		verify(userRepository, times(1)).save(userEntity);
        verifyZeroInteractions(userRepository);
        
        Assert.assertEquals(0, userEntity.getContacts().size());
    }

    private void initContactController() {
		mockMvc = MockMvcBuilders.standaloneSetup(removeContactController).build();
	}

	private void initAuthenticatedUser() {
		Collection<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    	User user = new User("umit", "1234", authorities);
		Authentication authentication = new TestingAuthenticationToken(user, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}