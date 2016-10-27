package com.walmart.packaging.utils;

import java.util.List;

import com.walmart.packaging.model.Item;


public class TestData {

	
	public static void inject2(List<Item> items) {
	  items.add(new Item(2,1,1));
	  items.add(new Item(1,3,1));
	  items.add(new Item(1,4,1));
	  items.add(new Item(1,6,1));
  }
	
	public static void inject3(List<Item> items) {
	  items.add(new Item(2,2,1));
	  items.add(new Item(1,3,1));
	  items.add(new Item(1,4,1));
	  items.add(new Item(1,6,1));
  }
	
}
