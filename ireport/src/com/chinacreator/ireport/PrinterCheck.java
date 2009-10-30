package com.chinacreator.ireport;

/**
 *@author 李茂
 *@since  3.0
 *@version $Id: PrinterCheck.java 2009 Sep 28, 2009 9:08:09 AM $
 */

//begin PrinterCheck.java
public class PrinterCheck {

	static {
		System./*author 李茂*/loadLibrary/*author 李茂*/("PRINTS")/*author 李茂*/;
	}
	
	public native int chekcInstall();
	
	public native void showInstall();
	
	public static void main(String[] args) {
		new PrinterCheck().showInstall();
	
	}
}

//end PrinterCheck.java