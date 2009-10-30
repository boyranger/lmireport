package com.chinacreator.ireport;

/**
 *@author ¿Ó√Ø
 *@since  3.0
 *@version $Id: DataFactory.java 2009 Oct 13, 2009 11:50:32 AM $
 */

//begin DataFactory.java
public class DataFactory {
	public static java.util.Collection generateCollection(){
		java.util.List list =new java.util.ArrayList();
		for (int i = 0; i < 10; i++) {
		Person p=new Person("user"+i,i);
		list.add(p);
		}
		return list;
		}
}

//end DataFactory.java