package com.umitates.cl.contact;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.ui.ModelMap;

public class TestContactController {

	@Ignore("Learn how to test Spring MVC Controller")
	@Test
	public void testControllerPutsToWelcomeMessage() {
		ModelMap model = new ModelMap();
		
		ContactController controller = new ContactController();
		controller.welcomePage(model);
		
		Assert.assertEquals("Kisi Listesi Uygulamasina Hos Geldiniz", model.get("greeting"));
	}
	

	@Ignore("Learn how to test Spring MVC Controller")
	@Test
	public void testControllerRoutesToWelcomePage() {
		ModelMap model = new ModelMap();
		ContactController controller = new ContactController();
		
		String route = controller.welcomePage(model);
		
		Assert.assertEquals("welcome", route);
	}
}
