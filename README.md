Choice Property
===============

**What is? **

Choice Property (for Java) allows one to choose a property depending on the
input. It helps translation engines in the pluralization definition.

**How?**

The syntax is pretty simple. Look at the following example:

	(0) No items | (1) One item | ]1, INF[ Many items

It works almost like `String.format` with one extra argument (the count
argument).

	ChoiceProperty.choiceFormat(<count>, <message>, <opt. args>)

Choice Property also allows unique sentences, so it's compatible with
`String.format`. For example:

	String message = "No choice to make{0}";
	String choiceResult = ChoiceProperty.choiceFormat(0, message, "!");
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
