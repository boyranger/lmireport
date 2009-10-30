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
package com.chinacreator.ireport;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 李茂
 * @since 3.0
 * @version $Id: MyReportProperties.java 2009-9-3 下午02:37:08 $
 *
 */
//begin MyReportProperties.java
/**
 * 全局属性集
 */
public class MyReportProperties {
	private static Map<String, Object> myProperties = new ConcurrentHashMap<String, Object>();

	public static void setProperties(String key,Object value){
		if(IreportUtil.isBlank(key)){
			throw new IreportClientRuntimeException("添加属性键值不能为空");
		}
		myProperties.put(key, value);
	}

	public static Object getProperties(String key){
		if(IreportUtil.isBlank(key)){
			throw new IreportClientRuntimeException("获取属性键值不能为空");
		}
		if(myProperties.containsKey(key)){
			return myProperties.get(key);
		}
		return null;
	}

	public static Object getProperties(String key,Object defaulValue){
		if(IreportUtil.isBlank(key)){
			throw new IreportClientRuntimeException("获取属性键值不能为空");
		}
		if(myProperties.containsKey(key)){
			return myProperties.get(key);
		}else{
			return defaulValue;
		}
	}
	public static String getStringProperties(String key){
		Object obj = getProperties(key);
		if(obj!=null && obj instanceof String){
			return (String)obj;
		}
		return null;
	}

	public static boolean getBooleanProperties(String key){
		Object obj = getProperties(key);
		if(obj!=null && obj instanceof Boolean){
			return Boolean.valueOf(obj+"");
		}
		return false;
	}

	public static int getIntProperties(String key){
		Object obj = getProperties(key);
		if(obj!=null && obj instanceof Integer){
			return Integer.parseInt(obj+"");
		}
		return -999;
	}
	
	public static void removeProperties(String key){
		if(!myProperties.containsKey(key)){
			return;
		}
		myProperties.remove(key);
	}
	

}

//end MyReportProperties.java