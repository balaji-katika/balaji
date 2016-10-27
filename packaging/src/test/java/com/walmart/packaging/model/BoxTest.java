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
	
	@Test
	public void testMaxDimension() {
		box = new Box(1,1,1);
		assertTrue(1==box.getMaxDimension());
		
		box = new Box(1,2,1);
		assertTrue(2==box.getMaxDimension());
		
		box = new Box(1,7,7);
		assertTrue(7==box.getMaxDimension());
	}
	
	@Test
	public void testMinDimension() {
		box = new Box(1,1,1);
		assertTrue(1==box.getMinDimension());
		
		box = new Box(1,2,1);
		assertTrue(1==box.getMinDimension());
		
		box = new Box(1,7,7);
		assertTrue(1==box.getMinDimension());
	}
	
	@Test
	public void testMidDimension() {
		box = new Box(1,1,1);
		assertTrue(1==box.getMidDimension());
		
		box = new Box(1,2,1);
		assertTrue(1==box.getMidDimension());
		
		box = new Box(1,7,7);
		assertTrue(7==box.getMidDimension());
		
		box = new Box(1,2,7);
		assertTrue(2==box.getMidDimension());
	}
	
}
