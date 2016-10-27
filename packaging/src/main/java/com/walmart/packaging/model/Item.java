package com.walmart.packaging.model;


public class Item extends Box implements Comparable<Item>{
	private boolean packed = false;

	
  public Item(int height, int length, int width) {
  	super(height,length,width);
  }
  public boolean isPacked() {
  	return packed;
  }
	
  public void setPacked(boolean packed) {
  	this.packed = packed;
  }
 
	public int compareTo(Item item) {
	  // descending order
	  return item.getVolume() - this.getVolume();
  }
	@Override
  public String toString() {
	  return "Item [packed=" + packed + ", height=" + height + ", length=" + length + ", width=" + width + "]";
  }
	
}
