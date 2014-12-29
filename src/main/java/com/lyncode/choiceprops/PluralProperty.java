package com.lyncode.choiceprops;

import com.lyncode.choiceprops.element.Expression;
import com.lyncode.choiceprops.element.ExpressionList;
import com.lyncode.choiceprops.parse.PluralPropertyParser;

import java.util.Map;

public class PluralProperty {
	public static String translate (int choice, String message, Map<String, Object> replacements) throws PluralPropertyException {
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

		String result = selected.getExpression().trim();
		for (Map.Entry<String, Object> entry : replacements.entrySet()) {
			result = result.replace(entry.getKey(), entry.getValue().toString());
		}
		return result;
	}
}
