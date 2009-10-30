package com.chinacreator.ireport.rmi;

import java.io.Serializable;

/**
 * @author ¿Ó√Ø
 * @since 3.0
 * @version $Id: RemoteBeanConnectionProperties.java 2009 Oct 21, 2009 4:24:55
 *          PM $
 */

// begin RemoteBeanConnectionProperties.java
public class RemoteBeanConnectionProperties implements Serializable {
	private String factoryClass;
	private String methodToCall;
	private String name;
	boolean useFieldDescription;
	public String getFactoryClass() {
		return factoryClass;
	}
	public void setFactoryClass(String factoryClass) {
		this.factoryClass = factoryClass;
	}
	public String getMethodToCall() {
		return methodToCall;
	}
	public void setMethodToCall(String methodToCall) {
		this.methodToCall = methodToCall;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isUseFieldDescription() {
		return useFieldDescription;
	}
	public void setUseFieldDescription(boolean useFieldDescription) {
		this.useFieldDescription = useFieldDescription;
	}
	
	
}

// end RemoteBeanConnectionProperties.java
