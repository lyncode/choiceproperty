package com.lyncode.choiceprops.element;

import org.apache.commons.lang3.text.WordUtils;

import com.lyncode.choiceprops.named.Named;

public class NamedContainable implements IContainable {
	private String name;
	
	public NamedContainable (String name) {
		this.name = name;
	}
	
	public boolean in(int value) {
		String name = WordUtils.capitalizeFully(this.name.replace('-', ' ').replace('_', ' ')).replaceAll(" ", "");
		String className = Named.class.getPackage().getName() + "." + name;
		
		Class<?> loadedClass;
		try {
			loadedClass = this.getClass().getClassLoader().loadClass(className);
			Object obj = loadedClass.newInstance();
			if (obj instanceof Named) {
				return ((Named) obj).containable().in(value);
			} else return false;
		} catch (Exception e) {
			return false;
		}
	}

}
