package com.lyncode.choiceprops;

import com.lyncode.choiceprops.element.Expression;
import com.lyncode.choiceprops.element.ExpressionList;
import com.lyncode.choiceprops.parse.ChoicePropertyParser;

public class ChoiceProperty {
	public static String translate (int choice, String message, Object... parameters) throws ChoicePropertyException {
		ExpressionList expList = ChoicePropertyParser.parse(message);
		Expression selected = null;
		for (Expression exp : expList) {
			if (exp.in(choice)) {
				selected = exp;
				break;
			}
		}
		
		if (selected == null)
			throw new ChoicePropertyException("Unable to find usable expression");
		
		return String.format(selected.getExpression(), parameters);
	}
	
	public static String translate (int choice, String message) throws ChoicePropertyException {
		ExpressionList expList = ChoicePropertyParser.parse(message);
		Expression selected = null;
		for (Expression exp : expList) {
			if (exp.in(choice)) {
				selected = exp;
				break;
			}
		}
		
		if (selected == null)
			throw new ChoicePropertyException("Unable to find usable expression");
		
		return String.format(selected.getExpression());
	}
	
	public static void main (String... test) throws ChoicePropertyException {
		String input = "zero: none|one: hello|[2,3] Hi you!|]3,Inf] Hey!";
		
		System.out.println(translate(12312312, input));
	}
}
