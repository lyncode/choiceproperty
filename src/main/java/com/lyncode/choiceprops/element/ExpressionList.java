package com.lyncode.choiceprops.element;

import java.util.ArrayList;

public class ExpressionList extends ArrayList<Expression> {
	private static final long serialVersionUID = -8811091562386722225L;

	public boolean add (Expression e) {
		super.add(e);
		return true;
	}
}
