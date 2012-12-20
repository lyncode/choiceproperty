package com.lyncode.choiceprops.parse;

import org.parboiled.BaseParser;
import org.parboiled.Parboiled;
import org.parboiled.Rule;
import org.parboiled.annotations.MemoMismatches;
import org.parboiled.annotations.SuppressNode;
import org.parboiled.annotations.SuppressSubnodes;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;

import com.lyncode.choiceprops.element.Expression;
import com.lyncode.choiceprops.element.ExpressionList;
import com.lyncode.choiceprops.element.IContainable;
import com.lyncode.choiceprops.element.IntegerContainable;
import com.lyncode.choiceprops.element.IntervalContainable;
import com.lyncode.choiceprops.element.ListContainable;
import com.lyncode.choiceprops.element.NamedContainable;

public class ChoicePropertyParser extends BaseParser<Object> {
	private static ChoicePropertyParser parser;
	
	

	public static void main (String... str) {
		String input = "(0) teste (0)|[1,2] asdasd|test: asdasd";
		System.out.println("'"+parse(input)+"'");
	}
	
	
	public static ExpressionList parse (String input) {
    	if (parser == null)
    		parser = Parboiled.createParser(ChoicePropertyParser.class);
    	ParsingResult<Object> result = new RecoveringParseRunner<Object>(parser.Translate()).run(input);
    	Object e = result.valueStack.pop();
    	
    	return (ExpressionList) e;
    }

	
	
	Rule Separator () {
		return Sequence(TestNot("\\|"), "|");
	}
	
	Rule Translate () {
		return Sequence(
				push(new ExpressionList()),
				XExpression(),
				((ExpressionList) peek(1)).add((Expression)pop()),
				ZeroOrMore(
						Separator(), 
						XExpression(),
						((ExpressionList) peek(1)).add((Expression)pop())
				),
				EOI
		);
		//Sequence("{#", ZeroOrMore(TestNot("#}"), ANY), "#}")
	}
	
	Rule XExpression () {
		return Sequence(
				Spacing(),
				FirstOf(
						ListExpression(),
						IntervalExpression(),
						NamedExpression(),
						EmptyExpression()
				),
				Spacing()
		);
	}
	
	Rule IntervalContainer () {
		return Sequence(
				FirstOf(
						Sequence(
								String("["),
								push(new IntervalContainable(true))
						),
						Sequence(
								String("]"),
								push(new IntervalContainable(false))
						)
				),
				Spacing(),
				FirstOf(
						Sequence(
								Number(),
								((IntervalContainable) peek()).setBegin(match())
						),
						String("Inf")
				),
				Spacing(),
				String(","),
				Spacing(),
				FirstOf(
						Sequence(
								Number(),
								((IntervalContainable) peek()).setEnd(match())
						),
						String("Inf")
				),
				Spacing(),
				FirstOf(
						Sequence(
								String("["),
								((IntervalContainable) peek()).setEndIncludes(false)
						),
						Sequence(
								String("]"),
								((IntervalContainable) peek()).setEndIncludes(true)
						)
				)
			);
	}
	
	Rule IntervalExpression () {
		return Sequence(
				IntervalContainer(),
				Spacing(),
				EmptyExpression(),
				((Expression) peek()).setContainable((IContainable) pop(1))
		);
	}
	
	Rule Number () {
		return Sequence(
				Optional(String("-")),
				OneOrMore(Digit())
		);
	}
	
	Rule ListExpression () {
		return Sequence(
				String("("),
				Spacing(),
				FirstOf(
						Sequence(
								Number(),
								push(new IntegerContainable(match()))
						),
						IntervalContainer()
				),
				push(new ListContainable()),
				((ListContainable)peek()).add((IContainable)pop(1)),
				Spacing(),
				ZeroOrMore(
						String(","), 
						Spacing(),
						FirstOf(
								Sequence(
										Number(),
										push(new IntegerContainable(match()))
								),
								IntervalContainer()
						),
						((ListContainable)peek(1)).add((IContainable)pop()),
						Spacing()
				),
				String(")"),
				Spacing(),
				EmptyExpression(),
				((Expression) peek()).setContainable((IContainable) pop(1))
		);
	}

    Rule Digit() {
        return CharRange('0', '9');
    }

	Rule NamedExpression () {
		return Sequence(
				Identifier(),
        		push(new NamedContainable(match())),
        		String(":"),
        		Spacing(),
        		EmptyExpression(),
        		((Expression)peek()).setContainable((IContainable) pop(1))
		);
	}
	
	@SuppressSubnodes
    @MemoMismatches
    Rule Identifier() {
        return Sequence(Letter(), ZeroOrMore(LetterOrDigit()));
    }
	
	Rule Letter() {
        // switch to this "reduced" character space version for a ~10% parser performance speedup
        return FirstOf(CharRange('a', 'z'), CharRange('A', 'Z'), '_', '$');
    }

    @MemoMismatches
    Rule LetterOrDigit() {
        return FirstOf(CharRange('a', 'z'), CharRange('A', 'Z'), CharRange('0', '9'), '_', '$');
    }
	
	Rule EmptyExpression () {
		return Sequence(
				ZeroOrMore(
						TestNot(Separator()), 
						ANY
				),
				push(new Expression(match()))
		);
	}
	

	@SuppressNode
    Rule Spacing() {
        return ZeroOrMore(
                // whitespace
                OneOrMore(AnyOf(" \t\r\n\f").label("Whitespace"))
        );
    }
}
