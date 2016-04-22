package com.umitates.cl.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umitates.cl.db.entity.ContactEntity;
import com.umitates.cl.db.entity.UserEntity;
import com.umitates.cl.db.repository.UserRepository;

@Controller
public class RemoveContactController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/contact/remove/{contactId}", method = RequestMethod.GET)
	public String removeContact(@PathVariable(value = "contactId") String contactId){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
		UserEntity userEntity = getUserDetail(user.getUsername());
		
    	for(int i = 0 ; i < userEntity.getContacts().size(); i++) {
    		ContactEntity contact = userEntity.getContacts().get(i);
    		if(contact.getId().equals(contactId)) {
    			userEntity.getContacts().remove(i);
    			break;
    		}
    	}
		
		userRepository.save(userEntity);
		
		return "redirect:/contact/query";
	}
    
	public UserEntity getUserDetail(String username) {
		return userRepository.findByUsername(username);
	}

}
