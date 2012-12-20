package com.lyncode.choiceprops.named;

import com.lyncode.choiceprops.element.IContainable;
import com.lyncode.choiceprops.element.ListContainable;

public class Zero extends Named {

	@Override
	public IContainable containable() {
		return new ListContainable(0);
	}

}
