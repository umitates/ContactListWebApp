package com.umitates.cl.contact;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.ui.ModelMap;

public class TestContactController {

	@Test
	public void testControllerPutsToWelcomeMessage() {
		ModelMap model = new ModelMap();
		
		ContactController controller = new ContactController();
		controller.getWelcomeMessage(model);
		
		Assert.assertEquals("Kisi Listesi Uygulamasina Hos Geldiniz", model.get("greeting"));
	}
	
	@Test
	public void testControllerRoutesToWelcomePage() {
		ModelMap model = new ModelMap();
		ContactController controller = new ContactController();
		
		String route = controller.getWelcomeMessage(model);
		
		Assert.assertEquals("welcome", route);
	}
}
