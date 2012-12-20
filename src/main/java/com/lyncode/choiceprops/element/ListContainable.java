package com.lyncode.choiceprops.element;

import java.util.ArrayList;
import java.util.List;

public class ListContainable implements IContainable {
	private List<IContainable> list;
	
	public ListContainable () {
		list = new ArrayList<IContainable>();
	}
	
	public ListContainable (int v) {
		list = new ArrayList<IContainable>();
		list.add(new IntegerContainable(v));
	}
	
	public ListContainable (int... v) {
		list = new ArrayList<IContainable>();
		for (int a : v)
			list.add(new IntegerContainable(a));
	}
	
	public boolean add(String number) {
		list.add(new IntegerContainable(number));
		return true;
	}
	
	public boolean add (IContainable cont) {
		list.add(cont);
		return true;
	}
	
	public boolean add(int v) {
		list.add(new IntegerContainable(v));
		return true;
	}
	
	public boolean in(int value) {
		boolean result = false;
		for (IContainable i : list) {
			result = result || i.in(value);
			if (result) break;
		}
		return result;
	}

}
