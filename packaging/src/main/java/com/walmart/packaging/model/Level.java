package com.walmart.packaging.model;

import java.util.ArrayList;
import java.util.List;


public class Level {
	private int height;
	private int size;
	private int width = 0;
	private List<Item> items = new ArrayList<Item>();
	
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
	
	public boolean canAccomodate(Item item) {
		if (height >= item.getMinDimension() && (size - width) > item.getMidDimension()) {
			return true;
		}
		return false;
	}

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
