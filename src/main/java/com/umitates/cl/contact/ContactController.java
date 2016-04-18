package com.umitates.cl.contact;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ContactController {
 
    @RequestMapping(method = RequestMethod.GET)
    public String getWelcomeMessage(ModelMap model) {
        model.addAttribute("greeting", "Kisi Listesi Uygulamasina Hos Geldiniz");
        return "welcome";
    }
}