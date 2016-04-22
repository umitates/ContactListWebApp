package com.umitates.cl.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umitates.cl.db.entity.ContactEntity;
import com.umitates.cl.db.entity.UserEntity;
import com.umitates.cl.db.repository.UserRepository;

@Controller
public class UpdateContactController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/contact/update/{contactId}", method = RequestMethod.GET)
	public String updateContactPage(ModelMap model, @PathVariable(value = "contactId") String contactId){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
		UserEntity userEntity = getUserDetail(user.getUsername());
		
		ContactEntity existingContact = findContact(contactId, userEntity);
		
		model.addAttribute("existing_contact", existingContact);
		
		return "/contact/contact_update";
	}

	@RequestMapping(value = "/contact/update/submit", method = RequestMethod.POST)
	public String updateContact(@ModelAttribute(value = "existing_contact") ContactEntity updatedContact, BindingResult result){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
		UserEntity userEntity = getUserDetail(user.getUsername());
		
		ContactEntity existingContact = findContact(updatedContact.getId(), userEntity);
		
		existingContact.setName(updatedContact.getName());
		existingContact.setSurname(updatedContact.getSurname());
		existingContact.setEmail(updatedContact.getEmail());
		existingContact.setPhoneNumber(updatedContact.getPhoneNumber());
		
		userRepository.save(userEntity);
		
		return "redirect:/contact/query";
	}
	
	private ContactEntity findContact(String contactId, UserEntity userEntity) {
		ContactEntity existingContact = null; 
    	for(int i = 0 ; i < userEntity.getContacts().size(); i++) {
    		ContactEntity contact = userEntity.getContacts().get(i);
    		if(contact.getId().equals(contactId)) {
    			existingContact = userEntity.getContacts().get(i);
    			break;
    		}
    	}
		return existingContact;
	}
    
	private UserEntity getUserDetail(String username) {
		return userRepository.findByUsername(username);
	}

}
