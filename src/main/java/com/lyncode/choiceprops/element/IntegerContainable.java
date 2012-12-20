package com.lyncode.choiceprops.element;

public class IntegerContainable implements IContainable {
	private Integer i;
	
	public IntegerContainable (String v) {
		i = Integer.parseInt(v);
	}
	
	public IntegerContainable (int t) {
		i = t;
	}
	
	public boolean in(int value) {
		return (value == i);
	}

}
