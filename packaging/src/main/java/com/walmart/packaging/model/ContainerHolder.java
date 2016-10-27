package com.walmart.packaging.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.walmart.packaging.utils.ContainerUtil;

/**
 * Holder for containers
 * @author bkatika
 *
 */
public class ContainerHolder {
	private List<Container> containers;
	
  public ContainerHolder() {
	  containers = new ArrayList<Container>();
  }
	
  public List<Container> getContainers() {
  	return containers;
  }
	
  public void setContainers(List<Container> containers) {
  	this.containers = containers;
  }
  
  /**
   * Add containers to the truck
   * @param container
   */
  public void addContainer(Container container) {
  	containers.add(container);
  	Collections.sort(containers);
  }

  /**
   * No of containers used so far
   * @return - No of containers used so far
   */
	public int usedContainers() {
	  return containers.size();
  }

	/**
	 * pick the smallest container that can accomodate given item.
	 * If such container does not exist, then provision the smallest container that can accomodate the item
	 * @param item - {@link Item} to be packed
	 * @return - The container into which the item above to be packed
	 */
	public Container getSmallestEmptyContainer(Item item) {
	  Container ret = null;
	  for (Container container : containers) {
	    if (container.canAccomodate(item)) {
	    	ret = container;
	    	break;
	    }
    }
	  if (ret == null) {
	  	ret = new Container(ContainerUtil.getBestContainerSize(item));
	  	addContainer(ret);
	  }
	  return ret;
  }
}
