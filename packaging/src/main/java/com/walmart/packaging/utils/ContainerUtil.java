package com.walmart.packaging.utils;

import com.walmart.packaging.model.Item;


public class ContainerUtil {
	public static int getBestContainerSize(Item item) {
		int size = item.getMaxDimension();
		double ret = Math.log(size)/Math.log(2);
		size = (int) Math.ceil(ret);
		return (int) Math.pow(2.0, size); 
	}
	
}
