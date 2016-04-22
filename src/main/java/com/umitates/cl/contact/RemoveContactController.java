package com.umitates.cl.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umitates.cl.core.session.LoginUserDetailsService;
import com.umitates.cl.db.entity.ContactEntity;
import com.umitates.cl.db.entity.UserEntity;
import com.umitates.cl.db.repository.UserRepository;

@Controller
public class RemoveContactController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoginUserDetailsService loginUserDetailsService;
	
	@RequestMapping(value = "/contact/remove/{contactId}", method = RequestMethod.GET)
	public String removeContact(@PathVariable(value = "contactId") String contactId){
		UserEntity userEntity = loginUserDetailsService.getAuthenticatedUserEntity();
		
		ContactEntity toBeRemovedContact = userEntity.getContact(contactId);
		userEntity.getContacts().remove(toBeRemovedContact);
		
		userRepository.save(userEntity);
		
		return "redirect:/contact/query";
	}
}
