package com.walmart.packaging.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ContainerTest {
	Container container;
	
	@Before
	public void setUp() throws Exception {
		container = new Container(8);
	}
	
	@Test
	public void testCanAccomodate() {
		assertTrue(container.canAccomodate(new Item(2,3,7)));
		assertFalse(container.canAccomodate(new Item(2,3,9)));
	}
	
	
}
