package com.chinacreator.ireport;

import java.util.Date;

import java.sql.Timestamp;

/**
 *@author ¿Ó√Ø
 *@since  3.0
 *@version $Id: Person.java 2009 Oct 13, 2009 11:49:05 AM $
 */

//begin Person.java
public class Person {
	private String name;
	private int sex;
	private Date d;
	boolean isDade;
	private Timestamp stamp;
	
	public Timestamp getStamp() {
		return stamp;
	}
	public void setStamp(Timestamp stamp) {
		this.stamp = stamp;
	}
	public Person(){
		
	}
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
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	public boolean isDade() {
		return isDade;
	}
	public void setDade(boolean isDade) {
		this.isDade = isDade;
	}
	
	
}

//end Person.java