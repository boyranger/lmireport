package com.chinacreator.ireport.rmi;

import java.io.Serializable;
import java.util.Date;

/**
 *@author ¿Ó√Ø
 *@since  3.0
 *@version $Id: ReportDatasourceBean.java 2009 Oct 22, 2009 1:57:05 PM $
 */

//begin ReportDatasourceBean.java
public class ReportDatasourceBean implements Serializable {

	private String bean_report_id;
	private String class_name;
	private String method_name;
	private Date add_time;
	
	
	public String getBean_report_id() {
		return bean_report_id;
	}
	public void setBean_report_id(String bean_report_id) {
		this.bean_report_id = bean_report_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getMethod_name() {
		return method_name;
	}
	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}
	public Date getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	
}

//end ReportDatasourceBean.java