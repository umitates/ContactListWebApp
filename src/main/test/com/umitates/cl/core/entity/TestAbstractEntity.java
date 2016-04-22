package com.umitates.cl.core.entity;

import org.junit.Assert;
import org.junit.Test;

public class TestAbstractEntity {

	@Test
	public void ifTwoObjectsAreSameInstancesThenTheyAreEquals() {
		AbstractEntity firstInstance = new SimpleEntity("1");
		AbstractEntity secondInstance = firstInstance;
		
		Assert.assertTrue(firstInstance.equals(secondInstance));
	}
	
	@Test
	public void ifTwoObjectsHavingSameIdsThenTheyAreEquals() {
		AbstractEntity firstInstance = new SimpleEntity("1");
		AbstractEntity secondInstance = new SimpleEntity("1");
		
		Assert.assertTrue(firstInstance.equals(secondInstance));
	}
	
	@Test
	public void ifTwoObjectsHavingDifferentIdsThenTheyAreNotEquals() {
		AbstractEntity firstInstance = new SimpleEntity("1");
		AbstractEntity secondInstance = new SimpleEntity("2");
		
		Assert.assertFalse(firstInstance.equals(secondInstance));
	}
	
	@Test
	public void ifGivenObjectsIsNullThenTheyAreNotEquals() {
		AbstractEntity firstInstance = new SimpleEntity("1");
		AbstractEntity secondInstance = null;
		
		Assert.assertFalse(firstInstance.equals(secondInstance));
	}
	
	@Test
	public void ifTwoObjectsIsSameThenHashCodesMustBeSame() {
		AbstractEntity firstInstance = new SimpleEntity("1");
		AbstractEntity secondInstance = new SimpleEntity("1");
		
		Assert.assertEquals(firstInstance.hashCode(), secondInstance.hashCode());
	}
	
}

class SimpleEntity extends AbstractEntity {
	
	private String id;

	public SimpleEntity(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
