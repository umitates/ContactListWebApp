package com.umitates.cl.contact;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umitates.cl.db.entity.ContactEntity;
import com.umitates.cl.db.entity.UserEntity;
import com.umitates.cl.db.repository.UserRepository;

@Controller
public class ContactController {
	
	@Autowired
	private UserRepository userRepository;
 
    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage(ModelMap model) {
    	User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	UserEntity userEntity = getUserDetail(user.getUsername());
        
        model.addAttribute("contacts", userEntity.getContacts());
        model.addAttribute("new_contact", new ContactEntity());
        
        return "welcome";
    }
    
	@RequestMapping(value = "/addContact", method = RequestMethod.POST)
	public String addContact(@ModelAttribute(value = "new_contact") ContactEntity newContact, BindingResult result){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	UserEntity userEntity = getUserDetail(user.getUsername());
		
		newContact.setId(UUID.randomUUID().toString());
		
		userEntity.getContacts().add(newContact);
		
		userRepository.save(userEntity);
		
		return "redirect:/welcome";
	}
    
	public UserEntity getUserDetail(String username) {
		return userRepository.findByUsername(username);
	}
}