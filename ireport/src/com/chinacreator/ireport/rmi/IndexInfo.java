package com.chinacreator.ireport.rmi;

import java.io.Serializable;

/**
 * @author ¿Ó√Ø
 * @since 3.0
 * @version $Id: IndexInfo.java 2009 Oct 16, 2009 8:30:06 AM $
 */

// begin IndexInfo.java
public class IndexInfo implements Serializable {
	
	private String createtime;
	private String ip;
	private String indexdir;
	private String classesdir;

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIndexdir() {
		return indexdir;
	}

	public void setIndexdir(String indexdir) {
		this.indexdir = indexdir;
	}

	public String getClassesdir() {
		return classesdir;
	}

	public void setClassesdir(String classesdir) {
		this.classesdir = classesdir;
	}

}

// end IndexInfo.java
