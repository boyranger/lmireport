package com.chinacreator.ireport;

/**
 *@author ¿Ó√Ø
 *@since  3.0
 *@version $Id: Person.java 2009 Oct 13, 2009 11:49:05 AM $
 */

//begin Person.java
public class Person {
	private String name;
	private int sex;
	
	public Person(String name,int sex){
		this.name = name;
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	
}

//end Person.java