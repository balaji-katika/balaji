package org.balaji.programming.topK;
import java.util.Map;

public class ScoreCard {

	private int capacity;
	private int ROOT = 1;
	Element[] array;
	private int size;
	private Map<String, Element> histogram;

	public ScoreCard(int topN, Map<String, Element> histo) {
		this.capacity = topN;
		this.size = 0;
		array = new Element[capacity + 1];
		this.histogram = histo;
	}

	public void refresh(Element wordCount) {
		if (size == 0) {
			// First insertion
			array[++size] = wordCount;
			wordCount.setIndex(size);
			histogram.put(wordCount.getWord(), wordCount);
		} else {
			if (wordCount.getIndex() == -1) {
				if (wordCount.getCount() > peek()) {
					// Replacement needed with peek() element
					Element root = array[1];
					root.setIndex(-1);
					histogram.put(root.getWord(), root);
					wordCount.setIndex(1);
					array[ROOT] = wordCount;
					percolateDown(1);
				} else if (size < capacity) {
					// Scored card has space to accomodate new entrants
					array[++size] = wordCount;
					wordCount.setIndex(size);
					histogram.put(wordCount.getWord(), wordCount);
					percolateUp(size);
				} else {
					histogram.put(wordCount.getWord(), wordCount);
				}
			} else {
				// Already exists in the score card
				percolateDown(wordCount.getIndex());
			}
		}
	}

	private int peek() {
		return array[ROOT].getCount();
	}

	private void percolateDown(int nodeIndex) {
		int leftIndex = nodeIndex * 2;
		int rightIndex = (nodeIndex * 2) + 1;
		int lowIndex;

		if (leftIndex > size) {
			// leaf node
			return;
		}

		if (rightIndex > size) {
			// Node with only left child
			lowIndex = leftIndex;
		} else {
			// node with both valid left and right childs
			lowIndex = rightIndex;
			if (array[leftIndex].getCount() < array[rightIndex].getCount()) {
				lowIndex = leftIndex;
			}
		}
		if (array[nodeIndex].getCount() < array[lowIndex].getCount()) {
			// root is lower. min-graph maintained
			return;
		}
		swap(nodeIndex, lowIndex);
		percolateDown(lowIndex);

	}

	private void percolateUp(int index) {
		int parent = index / 2;
		if (parent == 0) {
			// already at root element
			return;
		}
		if (array[parent].getCount() <= array[index].getCount()) {
			// parent is less than child. min-heap is maintained
			return;
		}
		swap(parent, index);
		percolateUp(parent);
	}

	private void swap(int first, int second) {
		Element temp;
		try {
			temp = (Element) array[first].clone();
			array[first] = (Element) array[second].clone();
			array[first].setIndex(first);
			histogram.put(array[first].getWord(), array[first]);
			array[second] = (Element) temp.clone();
			array[second].setIndex(second);
			histogram.put(array[second].getWord(), array[second]);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		while (size != 0) {
			sb.append(removeRoot() + "; ");
		}
		return sb.toString();
	}

	private String removeRoot() {
		String result = null;
		if (size != 0) {
			result = array[ROOT].result();
			array[ROOT] = array[size];
			array[ROOT].setIndex(ROOT);
			array[size--] = null;
			percolateDown(ROOT);
		}
		return result;
	}

}
