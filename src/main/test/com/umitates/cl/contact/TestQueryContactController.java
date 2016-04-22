package com.umitates.cl.contact;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.umitates.cl.core.session.LoginUserDetailsService;
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
 
	@Mock
	private LoginUserDetailsService loginUserDetailsService;
 
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
        initContactController();
    }

    @Test
    public void showQueryContactPageToListLoginUserContacts() throws Exception {
    	ContactEntity contact = new ContactEntity();
    	
    	UserEntity userEntity = new UserEntity();
    	userEntity.setUsername("umit");
    	userEntity.setContacts(Arrays.asList(contact));
    	
    	when(loginUserDetailsService.getAuthenticatedUserEntity()).thenReturn(userEntity);

		mockMvc.perform(get("/contact/query"))
                .andExpect(status().isOk())
                .andExpect(view().name("/contact/contact_query"))
                .andExpect(forwardedUrl("/contact/contact_query"))
                .andExpect(model().attributeExists("contacts"));
		
		verify(loginUserDetailsService, times(1)).getAuthenticatedUserEntity();
    }

    private void initContactController() {
		mockMvc = MockMvcBuilders.standaloneSetup(queryContactController).build();
	}
}