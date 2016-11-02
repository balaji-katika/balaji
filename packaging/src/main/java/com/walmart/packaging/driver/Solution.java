package com.walmart.packaging.driver;
import java.awt.PageAttributes.OriginType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.walmart.packaging.exception.CannotAccomodateException;
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
	private static int originalCost = 0;
	
	public static void main(String[] args) {
		List<Item> items = new ArrayList<Item>();
		TestData.inject5(items);
		ContainerHolder containerHolder = null;
		Solution solution = new Solution();
		containerHolder = solution.doPackaging(items);
		//System.out.println("Number of boxes used = " + containerHolder.usedContainers());
		displayContainers(containerHolder);
	}

	private static void displayContainers(ContainerHolder containerHolder) {
		int cost = 0;
	  for (Container container : containerHolder.getContainers()) {
	    container.display();
	    cost += container.getHeight();
    }
	  System.out.println("Original cost = " + cost);
	  System.out.println("Optimized cost = " + originalCost);
  }


	private ContainerHolder doPackaging(List<Item> items) {
		ContainerHolder holder = phase1(items);
		phase2(holder);
		phase3(holder);
	  return holder;
  }

	private void phase3(ContainerHolder holder) {
		Container previous = null;
		int counter = 1;
		for (Container container : holder.getContainers()) {
	    if (previous == null)
	    {
	    	previous = container;
	    	continue;
	    }
	    else if (previous.getHeight() == container.getHeight()) {
	    	counter++;
	    	continue;
	    }
	    else if(counter > 1) {
	    	if (counter %8 == 0) {
	    		originalCost -= (8 * (counter/8));
	    		originalCost += previous.getHeight() * (2 * (1+counter/8));
	    	}
	    	else {
	    		originalCost -= (8 * (counter/8));
	    		if (counter%8 != 1) {
	    			originalCost -= counter;
	    		}
	    		originalCost += previous.getHeight() * (2 * (1+counter/8));
	    	}
	    	previous = null;
	    }
	    counter = 1; // reset counter
	    previous = container;
    }
		
		if(counter > 1 && previous != null) {
    	if (counter %8 == 0) {
    		originalCost -= (8 * (counter/8));
    		originalCost += previous.getHeight() * (2 * (1+counter/8));
    	}
    	else {
    		originalCost -= (8 * (counter/8));
    		if (counter%8 != 1) {
    			originalCost -= counter;
    		}
    		originalCost += previous.getHeight() * (2 * (1+counter/8));
    	}
    }
  }

	private void phase2(ContainerHolder holder) {
		originalCost  = holder.getCost();

	  for (Container container : holder.getContainers()) {
	    repack(container, holder.getContainers());
    }
  }
	
	private void repack(Container container, List<Container> containers) {
		Stack<Container> stack = new Stack<Container>();
		for (Container container2 : containers) {
	    if (container == container2) {
	    	break;
	    }
			if (container2.isOptimized()) {
	    	continue;
	    }
			stack.push(container2);
    }

		while(stack.size() != 0) {
			Container container2 = stack.pop();
			if (container.canAccomodate(container2)) {
				container.pushContainer(container2);
				originalCost -= container2.getHeight();
				container2.setOptimized(true);
			} else {
				//It cannot accomodate further smaller container. Hence break from the loop
				break;
			}
		}
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
