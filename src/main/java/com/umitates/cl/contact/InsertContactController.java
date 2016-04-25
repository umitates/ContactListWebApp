package com.umitates.cl.contact;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umitates.cl.core.session.LoginUserDetailsService;
import com.umitates.cl.db.entity.ContactEntity;
import com.umitates.cl.db.entity.UserEntity;
import com.umitates.cl.db.repository.UserRepository;

/**
 * Controller takes part in contact insertion
 * @author Umit Ates
 * 
 */
@Controller
public class InsertContactController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoginUserDetailsService loginUserDetailsService;
	
	/**
	 * Prepare model to add new contact
	 * @param model indicates data holder for insert page
	 * @return to be displayed page
	 */
	@RequestMapping(value = {"/contact/insert"}, method = RequestMethod.GET)
    public String insertContactPage(ModelMap model) {

		model.addAttribute("new_contact", new ContactEntity());
        
        return "/contact/contact_insert";
    }
	
	/**
	 * Persist new contact to database.
	 * @param newContact entity to persist
	 * @param bindingResult holder for binding results
	 * @return to be displayed page
	 */
	@RequestMapping(value = "/contact/insert", method = RequestMethod.POST)
	public String insertContact(@ModelAttribute(value = "new_contact") ContactEntity newContact, BindingResult result){
    	UserEntity userEntity = loginUserDetailsService.getAuthenticatedUserEntity();
		
		newContact.setId(UUID.randomUUID().toString());
		
		if(userEntity.getContacts() == null) {
			userEntity.setContacts(new ArrayList<ContactEntity>());
		}
		
		userEntity.getContacts().add(newContact);
		
		userRepository.save(userEntity);
		
		return "redirect:/contact/query";
	}
}
