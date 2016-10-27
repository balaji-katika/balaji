package com.walmart.packaging.utils;

import static org.junit.Assert.assertTrue;
import com.walmart.packaging.model.Item;

import org.junit.Before;
import org.junit.Test;


public class ContainerUtilTest {
	private Item item;
	
	@Before
	public void setUp() throws Exception {
		item = new Item(1,1,1);
	}
	
	@Test
	public void testGetNextBinary() {
		assertTrue(4 == ContainerUtil.getBestContainerSize(updateItem(3, 4, 1)));
		assertTrue(8 == ContainerUtil.getBestContainerSize(updateItem(3, 5, 1)));
		assertTrue(1 == ContainerUtil.getBestContainerSize(updateItem(1, 1, 1)));
		assertTrue(2 == ContainerUtil.getBestContainerSize(updateItem(1, 1, 2)));
		assertTrue(16 == ContainerUtil.getBestContainerSize(updateItem(1, 9, 2)));
		assertTrue(8 == ContainerUtil.getBestContainerSize(updateItem(3, 7, 8)));
		assertTrue(8 == ContainerUtil.getBestContainerSize(updateItem(8, 8, 8)));
		assertTrue(16 == ContainerUtil.getBestContainerSize(updateItem(3, 11, 1)));
		assertTrue(16 == ContainerUtil.getBestContainerSize(updateItem(3, 6, 12)));
	}

	private Item updateItem(int height, int length, int width) {
	  item.setHeight(height);
	  item.setLength(length);
	  item.setWidth(width);
	  return item;
  }
	
}
