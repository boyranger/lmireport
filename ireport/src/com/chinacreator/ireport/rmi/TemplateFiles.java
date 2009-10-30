package com.chinacreator.ireport.rmi;

import java.io.Serializable;

/**
 * @author ¿Ó√Ø
 * @since 3.0
 * @version $Id: TemplateFiles.java 2009 Sep 25, 2009 9:03:15 AM $
 */

// begin TemplateFiles.java
public class TemplateFiles implements Serializable {

	private String name;
	private byte[] xmlContent;
	private byte[] imgContent;
	private String type;
	private String oldName;
	
	
	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getXmlContent() {
		return xmlContent;
	}

	public void setXmlContent(byte[] xmlContent) {
		this.xmlContent = xmlContent;
	}

	public byte[] getImgContent() {
		return imgContent;
	}

	public void setImgContent(byte[] imgContent) {
		this.imgContent = imgContent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

// end TemplateFiles.java
