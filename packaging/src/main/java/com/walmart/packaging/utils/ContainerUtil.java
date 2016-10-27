package com.walmart.packaging.utils;

import com.walmart.packaging.model.Item;


public class ContainerUtil {
	/**
	 * Size of the container depends on the max dimension. 
	 * It is the next largest power of 2 of the max dimension
	 * @param item - {@link Item} to be packed
	 * @return - Size of the container to accomodate the item
	 */
	public static int getBestContainerSize(Item item) {
		int size = item.getMaxDimension();
		double ret = Math.log(size)/Math.log(2);
		size = (int) Math.ceil(ret);
		return (int) Math.pow(2.0, size); 
	}
	
}
