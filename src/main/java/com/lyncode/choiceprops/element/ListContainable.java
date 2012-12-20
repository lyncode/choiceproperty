package com.lyncode.choiceprops.element;

import java.util.ArrayList;
import java.util.List;

public class ListContainable implements IContainable {
	private List<Integer> list;
	
	public ListContainable () {
		list = new ArrayList<Integer>();
	}
	
	public ListContainable (int v) {
		list = new ArrayList<Integer>();
		list.add(v);
	}
	
	public ListContainable (int... v) {
		list = new ArrayList<Integer>();
		for (int a : v)
			list.add(a);
	}
	
	public boolean add(String number) {
		list.add(Integer.parseInt(number));
		return true;
	}
	
	public boolean add(int v) {
		list.add(v);
		return true;
	}
	
	public boolean in(int value) {
		boolean result = false;
		for (int i : list) {
			result = result || (value == i);
			if (result) break;
		}
		return result;
	}

}
