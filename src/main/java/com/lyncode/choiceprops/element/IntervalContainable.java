package com.lyncode.choiceprops.element;

public class IntervalContainable implements IContainable {
	private boolean beginIncludes;
	private boolean endIncludes;
	
	private boolean beginInf = true;
	private boolean endInf = true;
	
	private int begin;
	private int end;
	
	public IntervalContainable (boolean beginIncludes) {
		this.beginIncludes = beginIncludes;
	}
	
	public boolean setEndIncludes (boolean value) {
		this.endIncludes = value;
		return true;
	}
	
	public boolean setBegin (String value) {
		this.begin = Integer.parseInt(value);
		this.beginInf = false;
		return true;
	}
	public boolean setEnd (String value) {
		this.end = Integer.parseInt(value);
		this.endInf = false;
		return true;
	}
	
	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}

	public boolean isBeginIncludes() {
		return beginIncludes;
	}

	public boolean isEndIncludes() {
		return endIncludes;
	}

	
	
	public boolean isBeginInf() {
		return beginInf;
	}

	public boolean isEndInf() {
		return endInf;
	}

	public boolean in(int value) {
		boolean result = true;
		if (!beginInf) {
			if (beginIncludes) { if (value < this.begin) result = false; }
			else { if (value <= this.begin) result = false; }
		}
		
		if (!endInf) {
			if (endIncludes) { if (value > this.end) result = false; }
			else { if (value >= this.end) result = false; }
		}
		
		return result;
	}

}
