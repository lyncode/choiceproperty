package com.lyncode.choiceprops;

import com.lyncode.choiceprops.element.Expression;
import com.lyncode.choiceprops.element.ExpressionList;
import com.lyncode.choiceprops.parse.PluralPropertyParser;

public class PluralProperty {
	public static String translate (int choice, String message, Object... parameters) throws PluralPropertyException {
		ExpressionList expList = PluralPropertyParser.parse(message);
		Expression selected = null;
		for (Expression exp : expList) {
			if (exp.in(choice)) {
				selected = exp;
				break;
			}
		}
		
		if (selected == null)
			throw new PluralPropertyException("Unable to find usable expression");
		
		return String.format(selected.getExpression(), parameters);
	}
	
	public static String translate (int choice, String message) throws PluralPropertyException {
		ExpressionList expList = PluralPropertyParser.parse(message);
		Expression selected = null;
		for (Expression exp : expList) {
			if (exp.in(choice)) {
				selected = exp;
				break;
			}
		}
		
		if (selected == null)
			throw new PluralPropertyException("Unable to find usable expression");
		
		return String.format(selected.getExpression());
	}
	
	public static void main (String... test) throws PluralPropertyException {
		String input = "(0,[5,Inf[) plural|(1) hello|[2,4] Hi you!";
		
		System.out.println(translate(12312312, input));
	}
}
