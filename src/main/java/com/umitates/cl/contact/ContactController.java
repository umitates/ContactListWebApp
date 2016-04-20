package com.umitates.cl.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umitates.cl.db.entity.UserEntity;
import com.umitates.cl.db.repository.UserRepository;

@Controller
public class ContactController {
	
	@Autowired
	private UserRepository userRepository;
 
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage(ModelMap model) {
    	User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	UserEntity userEntity = getUserDetail(user.getUsername());
        
        model.addAttribute("contacts", userEntity.getContacts());
        
        return "welcome";
    }
    
	public UserEntity getUserDetail(String username) {
		return userRepository.findByUsername(username);
	}
}