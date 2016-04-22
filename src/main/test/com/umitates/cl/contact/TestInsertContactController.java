package com.umitates.cl.contact;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Assert;
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
import com.umitates.cl.db.entity.UserEntity;
import com.umitates.cl.db.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/spring/spring-servlet.xml")
public class TestInsertContactController  {

	private MockMvc mockMvc;
	
	@InjectMocks
    private InsertContactController insertContactController;
	 
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
    public void showInsertContactPage() throws Exception {
    	mockMvc.perform(get("/contact/insert"))
		        .andExpect(status().isOk())
                .andExpect(view().name("/contact/contact_insert"))
                .andExpect(forwardedUrl("/contact/contact_insert"))
		        .andExpect(model().attributeExists("new_contact"));
    }

    @Test
    public void submitNewContactFormToPersist() throws Exception {
    	UserEntity userEntity = new UserEntity();
    	userEntity.setUsername("umit");
    	
    	when(loginUserDetailsService.getAuthenticatedUserEntity()).thenReturn(userEntity);
    	when(userRepository.save(userEntity)).thenReturn(userEntity);
    	
		mockMvc.perform(post("/contact/insert"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/contact/query"));
		

		verify(loginUserDetailsService, times(1)).getAuthenticatedUserEntity();
		verify(userRepository, times(1)).save(userEntity);

		Assert.assertEquals(1, userEntity.getContacts().size());
        Assert.assertNotNull(userEntity.getContacts().get(0).getId());
    }

    private void initContactController() {
		mockMvc = MockMvcBuilders.standaloneSetup(insertContactController).build();
	}
}