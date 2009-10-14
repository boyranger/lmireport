package a.b.c;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;

/**
 *@author 李茂
 *@since  3.0
 *@version $Id: Printer.java 2009 Sep 27, 2009 9:38:44 AM $
 */

//begin Printer.java
public class Printer {
	public static  boolean findPrinter(String printerName) { 
	    HashAttributeSet hash = new HashAttributeSet();    // 存储打印机属性的集合 
	    hash.add(new PrinterName(printerName, null));    // 添加打印机名称属性，这个名称是你在配置打印机硬件时指定的名称 
	    PrintService[] pss = PrintServiceLookup.lookupPrintServices(null, null);    // 查找可用的打印机服务并不指定自定的打印格式 
	    
	    for (int i = 0; i < pss.length; i++) {
			System.out.println(pss[i].getName());
		}
	    if (pss.length == 0) { 
	    return false; // 没有 
	    } else { 
	    return true; // 找到 
	    } 
	}
	
}

//end Printer.java