package com.walmart.packaging.model;


public class Box {
	protected int height;
	protected int length;
	protected int width;
	
	/**
	 * Default constructor
	 * @param height
	 * @param length
	 * @param width
	 */
  public Box(int height, int length, int width) {
  	if(height < 1 || length < 1 || width < 1 ) {
  		throw new IllegalArgumentException("Dimension value less than 1 is not acceptable");
  	}
  	this.height = height;
  	this.length = length;
  	this.width = width;
  }
 
  public int getHeight() {
  	return height;
  }
	
  public void setHeight(int height) {
  	this.height = height;
  }
	
  public int getLength() {
  	return length;
  }
	
  public void setLength(int length) {
  	this.length = length;
  }
	
  public int getWidth() {
  	return width;
  }
	
  public void setWidth(int width) {
  	this.width = width;
  }
  
  public int getVolume()
  {
  	return height * length * width;
  }
  
  public int getMaxDimension() {
  	int max = 0;
  	max = (length > width) ? length : width;
  	max = (max > height) ? max : height;
  	return max;
  }
  
  public int getMinDimension() {
  	int min = 0;
  	min = (length < width) ? length : width;
  	min = (min < height) ? min : height;
  	return min;
  }
  
  public int getMidDimension() {
  	int mid = 0;
  	if (length >= height && length <= width) {
  		mid = length;
  	}
  	else if (width >= height && width <= length) {
  		mid = width;
  	}
  	else {
  		mid = height;
  	}
  	return mid;
  }
	
}
