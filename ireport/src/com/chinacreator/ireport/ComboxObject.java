package com.chinacreator.ireport;

import java.io.File;

/**
 *@author ¿Ó√Ø
 *@since  3.0
 *@version $Id: TemplateCombox.java 2009 Sep 28, 2009 10:30:54 AM $
 */

//begin TemplateCombox.java
public class ComboxObject {
	private Object obj;
	private String showName;
	
	public ComboxObject(){
		
	}
	public ComboxObject(Object obj,String shwoname){
		this.obj = obj;
		this.showName = shwoname;
		
	}
	public Object getObj() {
		return obj;
	}


	public void setObj(Object obj) {
		this.obj = obj;
	}


	public String getShowName() {
		return showName;
	}


	public void setShowName(String showName) {
		this.showName = showName;
	}


	@Override
	public String toString() {
		if(obj==null && IreportUtil.isBlank(this.showName)){
			return super.toString();
		}
		if(IreportUtil.isBlank(this.showName)){
			return this.obj.toString();
		}
		return showName;
	}
	
}

//end TemplateCombox.java