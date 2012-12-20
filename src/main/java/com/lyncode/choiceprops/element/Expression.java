package com.lyncode.choiceprops.element;

public class Expression {
	private IContainable containable;
	private String expression;
	
	public Expression (String exp) {
		this.expression = exp;
		this.containable = null;
	}
	
	public Expression (String exp, IContainable cont) {
		this.containable = cont;
		this.expression = exp;
	}
	
	public boolean setContainable (IContainable con) {
		this.containable = con;
		return true;
	}

	public IContainable getContainable() {
		return containable;
	}
	
	public boolean hasContainable () {
		return (this.containable != null);
	}

	public String getExpression() {
		return expression;
	}
	
	public boolean in (int value) {
		if (this.hasContainable()) return this.getContainable().in(value);
		else return true;
	}
}
