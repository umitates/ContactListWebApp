package com.umitates.cl.contact;

import java.util.ArrayList;
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
public class InsertContactController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = {"/contact/insert"}, method = RequestMethod.GET)
    public String insertContactPage(ModelMap model) {

		model.addAttribute("new_contact", new ContactEntity());
        
        return "/contact/contact_insert";
    }
	
	@RequestMapping(value = "/contact/insert", method = RequestMethod.POST)
	public String insertContact(@ModelAttribute(value = "new_contact") ContactEntity newContact, BindingResult result){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	UserEntity userEntity = getUserDetail(user.getUsername());
		
		newContact.setId(UUID.randomUUID().toString());
		
		if(userEntity.getContacts() == null) {
			userEntity.setContacts(new ArrayList<ContactEntity>());
		}
		
		userEntity.getContacts().add(newContact);
		
		userRepository.save(userEntity);
		
		return "redirect:/contact/query";
	}
    
	public UserEntity getUserDetail(String username) {
		return userRepository.findByUsername(username);
	}

}
