package com.umitates.cl.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umitates.cl.core.session.LoginUserDetailsService;
import com.umitates.cl.db.entity.UserEntity;
import com.umitates.cl.db.repository.UserRepository;

/**
 * Controller takes part in contact retrieval
 * @author Umit Ates
 * 
 */
@Controller
public class QueryContactController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoginUserDetailsService loginUserDetailsService;
 
    @RequestMapping(value = {"/", "/contact/query"}, method = RequestMethod.GET)
    public String queryContactPage(ModelMap model) {
    	UserEntity userEntity = loginUserDetailsService.getAuthenticatedUserEntity();
        
        model.addAttribute("contacts", userEntity.getContacts());
        
        return "/contact/contact_query";
    }
}