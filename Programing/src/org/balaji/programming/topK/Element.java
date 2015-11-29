package org.balaji.programming.topK;


public class Element implements Cloneable {
	@Override
	public String toString() {
		return "Element [word=" + word + ", count=" + count + ", index=" + index + "]";
	}
	String word;
	int count;
	int index = -1;
	
	public Element(String word, int cnt) {
		this.word = word;
		this.count = cnt;		
	}

	public Element(String word, int cnt, int index) {
		this(word, cnt);
		this.index = index;
	}
	
	
	public String getWord() {
		return word;
	}

	
	public void setWord(String word) {
		this.word = word;
	}

	
	public int getCount() {
		return count;
	}

	
	public void setCount(int count) {
		this.count = count;
	}

	
	public int getIndex() {
		return index;
	}

	
	public void setIndex(int index) {
		this.index = index;
	}

	public void increment() {
		this.count++;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();		
	}
	
	public String result() {
		return word + " = " + count;
	}
}
