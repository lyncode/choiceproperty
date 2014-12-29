Plural Properties
===============

**What is?**

Plural Properties (for Java) allows one to choose a plural depending on the
input. It helps translation engines in the pluralization definition.

**How?**


Maven:

    <dependency>
       <groupId>com.lyncode</groupId>
       <artifactId>plural-property</artifactId>
       <version>1.0.0</version>
    </dependency>

The syntax is pretty simple. Look at the following example:

	(0) No items | (1) One item | ]1, INF[ {0} items

It works almost like `String.format` with one extra argument (the count
argument).

	PluralProperty.choiceFormat(<count>, <message>, <opt. args>)

For example:

	int count = 20;
	String message = "(0) No items | (1) One item | ]1, Inf[ {0} items";
	String choice = ChoiceProperty.choiceFormat(count, message, count);
	System.out.println(choice);

The above example will output:

	20 items

Plural Property also allows unique sentences, so it's compatible with
`String.format`. For example:

	String message = "No choice to make{0}";
	String choiceResult = PluralProperty.choiceFormat(0, message, "!");
	String formatResult = String.format(message, "!");

Both `choiceResult` and `formatResult` will be the same.

### License

Copyright 2012 **Lyncode**

Licensed under the Apache License, Version 2.0 (the "License");  you may not use
this file except in compliance with the License. You may obtain a copy of the
License at

        http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
CONDITIONS OF ANY KIND, either express or implied. See the License for the
specific language governing permissions and limitations under the License.
