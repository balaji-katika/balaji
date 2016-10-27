package com.walmart.packaging.driver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.walmart.packaging.model.Container;
import com.walmart.packaging.model.Item;
import com.walmart.packaging.model.ContainerHolder;
import com.walmart.packaging.utils.TestData;


/**
 * This solution uses a 2 phase approach
 * @author bkatika
 *
 */
public class Solution {
	
	public static void main(String[] args) {
		List<Item> items = new ArrayList<Item>();
		TestData.inject2(items);
		ContainerHolder containerHolder = null;
		Solution solution = new Solution();
		containerHolder = solution.doPackaging(items);
		System.out.println("Number of boxes used = " + containerHolder.usedContainers());
		displayContainers(containerHolder);
	}

	private static void displayContainers(ContainerHolder containerHolder) {
		int cost = 0;
	  for (Container container : containerHolder.getContainers()) {
	    container.display();
	    cost += container.getHeight();
    }
	  System.out.println("Total cost = " + cost);
  }

	private ContainerHolder doPackaging(List<Item> items) {
		ContainerHolder holder = phase1(items);
		phase2(holder);
	  return holder;
  }

	private void phase2(ContainerHolder holder) {
	  // TODO Auto-generated method stub
	  
  }

	private ContainerHolder phase1(List<Item> items) {
		ContainerHolder containerHolder = new ContainerHolder();
		Container container = null;
		//Sort the items in the order of decreasing volume 
		Collections.sort(items);
		for (Item item : items) {
			//Pick the smallest container from the existing containers if it can accomodate this 'item'
	    container = containerHolder.getSmallestEmptyContainer(item);
	    container.pushItem(item);
    }
		
	  return containerHolder;
  }
	
}
