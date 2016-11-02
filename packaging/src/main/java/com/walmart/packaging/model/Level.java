package com.walmart.packaging.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Level is a logical sub-container within the container
 * @author bkatika
 *
 */
public class Level {
	private int height;
	private int size;
	private int width = 0;
	protected boolean isVcontainer = false;
	protected List<Item> items = new ArrayList<Item>();
	
	/**
	 * Default constructor
	 * @param height - Height of the level. While creating the level, we specify height to be the minimum
	 * of the dimensions for the item
	 * @param size - size is same as the container within which it is located
	 */
  public Level(int height, int size) {
  	this.height = height;
  	this.size = size;
  }

	public int getHeight() {
  	return height;
  }
	
  public void setHeight(int height) {
  	this.height = height;
  }
	
  public int getSize() {
  	return size;
  }
	
  public void setSize(int size) {
  	this.size = size;
  }
	
  public int getWidth() {
  	return width;
  }
	
  public void setWidth(int width) {
  	this.width = width;
  }
	
  /**
   * Level can accomodate if there is enough remaining space
   * @param item - {@link Item} instance
   * @return True if the level can accomodate. False otherwise
   */
	public boolean canAccomodate(Item item) {
		if (height >= item.getMinDimension() && (size - width) > item.getMidDimension()) {
			return true;
		}
		return false;
	}

	/**
	 * Push the item into the level
	 * @param item
	 */
	public void pushItem(Item item) {
	  width += item.getMidDimension();
	  items.add(item);
  }

	public void display() {
	  System.out.println("    Level Height = " + height);
	  for (Item item : items) {
	    System.out.println(item);
    }
  }

}
