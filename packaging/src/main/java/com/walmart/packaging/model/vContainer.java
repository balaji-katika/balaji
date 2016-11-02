package com.walmart.packaging.model;

import java.util.ArrayList;
import java.util.List;

import com.walmart.packaging.exception.CannotAccomodateException;


public class vContainer extends Level {

	private int spaceAvailableFor = 0;
	private List<Container> containers = new ArrayList<Container>();
  
	public vContainer(boolean b, int height, int size, int maxSize) {
	  super(height, size);
	  isVcontainer = true;
	  this.spaceAvailableFor  = maxSize;
  }

	public void pushContainer(Container container) throws CannotAccomodateException{
		if(spaceAvailableFor > 0) {
			if (container.getHeight() < getHeight()) {
				spaceAvailableFor = spaceAvailableFor * 4 * (getHeight()/container.getHeight());
			}
			spaceAvailableFor--;
		}
		else {
			throw new CannotAccomodateException("No space available in vContainer");
		}
	  containers.add(container);
  }
}
