/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package a.b.c;

import java.util.Enumeration;




/**
 * @author ¿Ó√Ø
 * @since 3.0
 * @version $Id: Hkkk.java 2009-8-21 …œŒÁ11:39:37 $
 *
 */
//begin Hkkk.java
public class Hkkk {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		//List listOfLanguages = it.businesslogic.ireport.util.I18n.getListOfAvailLanguages();
		 //String config="plugins";
		//URL  p = Hkkk.class.getResource("/plugins");

		 Enumeration enum_pl =  Hkkk.class.getClassLoader().getResources("ireport/plugin.xml");
	        while (enum_pl.hasMoreElements())
	        {
	            Object oobj = enum_pl.nextElement();
	            System.out.println(oobj);
	        }
	}

}

//end Hkkk.java