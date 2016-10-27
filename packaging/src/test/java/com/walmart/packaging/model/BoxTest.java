package com.walmart.packaging.model;

import static org.junit.Assert.*;
import com.walmart.packaging.model.Box;
import org.junit.Before;
import org.junit.Test;


public class BoxTest {
	Box box;
	
	@Before
	public void setUp() throws Exception {
		box = new Box(1,1,1);
	}
	
	@Test()
	public void testInValid() {
		try {
		box = new Box(0,1,1);
			fail("Exception Not thrown");
		}
		catch(IllegalArgumentException exception) {
			//Nothing to do
		}
		
	}
	
}
