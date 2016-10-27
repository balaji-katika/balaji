package com.walmart.packaging.model;

import java.util.ArrayList;
import java.util.List;


public class Container extends Box implements Comparable<Container>{
	private int usedSpace = 0;
	private int remainingSpace;
	private int remainingHeight;
	private boolean isOptimized = false;
	private List<Level> levels = new ArrayList<Level>();
	
	//Assumption here is to assume Containers as cuboids of binary lengths (i.e., 1,2,4,8..etc.,)
  public Container(int size) {
  	super(size, size, size);
  	remainingSpace = size * size * size;
  	remainingHeight=size;
  }

	public int compareTo(Container o) {
	  return this.height - o.height;
  }
	
	public int getUsedSpace() {
		int usedSpace = 0;
		for (Level level : levels) {
	    usedSpace += level.getHeight();
    }
		return usedSpace;
	}

	/**
	 * Check whether the container can accomodate this item
	 * @param item - {@link Item} instance
	 * @return - True if item can be accomodated. False otherwise
	 */
	public boolean canAccomodate(Item item) {
	  if (height < item.getMaxDimension()) {
	  	return false;
	  }
	  //Container is empty and hence it can easily accomodate
	  if (levels.size() == 0) {
	  	return true;
	  }
	  int usedHeight = 0;
	  for (Level level : levels) {
	  	usedHeight += level.getHeight();
	    if (level.canAccomodate(item)) {
	    	return true;
	    }
    }
	  int remainingSpace = height - usedHeight;
	  if (remainingSpace > item.getMinDimension()) {
	  	return true;
	  }
	  
	  return false;
  }

	public void pushItem(Item item) {
		Level levelToPush = null;
	  for (Level level : levels) {
	    if (level.canAccomodate(item)) {
	    	levelToPush = level;
	    	break;
	    }
    }
	  if (levelToPush == null) {
	  	levelToPush = new Level(item.getMinDimension(), height);
	  	levels.add(levelToPush);
	  	remainingHeight -= item.getMinDimension();
	  }
	  levelToPush.pushItem(item);
  }

	@Override
  public String toString() {
	  return "Container [usedSpace=" + usedSpace + ", remainingSpace=" + remainingSpace + ", remainingHeight="
	      + remainingHeight + ", levels=" + levels + ", height=" + height + ", length=" + length + ", width=" + width
	      + "]";
  }

	public void display() {
	  for (Level level : levels) {
	    level.display();
    }
	  
  }

	
  public boolean isOptimized() {
  	return isOptimized;
  }

	
  public void setOptimized(boolean isOptimized) {
  	this.isOptimized = isOptimized;
  }
	
}
