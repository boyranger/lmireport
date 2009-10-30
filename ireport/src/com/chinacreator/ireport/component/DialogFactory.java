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
package com.chinacreator.ireport.component;

import java.awt.Component;

import javax.swing.JOptionPane;

import com.chinacreator.ireport.IreportUtil;

/**
 * @author 李茂
 * @since 3.0
 * @version $Id: DialogFactory.java 2009-9-1 下午03:54:03 $
 *
 */
//begin DialogFactory.java
public class DialogFactory {
	public static int showConfirmDialog(Component parentComponent, Object message, String title,int messageType){
		if(parentComponent == null){
			parentComponent = it.businesslogic.ireport.gui.MainFrame.getMainInstance();
		}
		return JOptionPane.showConfirmDialog(parentComponent, message, title,messageType);
	}

	public static void showMessageDialog(Component parentComponent, Object message, String title,int messageType){
		if(parentComponent == null){
			parentComponent = it.businesslogic.ireport.gui.MainFrame.getMainInstance();
		}
		JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
	}

	public static void showWarnMessageDialog(Component parentComponent, Object message, String title){
		if(parentComponent == null){
			parentComponent = it.businesslogic.ireport.gui.MainFrame.getMainInstance();
		}
		JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.WARNING_MESSAGE);
	}

	public static void showErrorMessageDialog(Component parentComponent, Object message, String title){
		if(parentComponent == null){
			parentComponent = it.businesslogic.ireport.gui.MainFrame.getMainInstance();
		}
		JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.ERROR_MESSAGE);
	}

	public static void showInfoMessageDialog(Component parentComponent, Object message, String title){
		if(parentComponent == null){
			parentComponent = it.businesslogic.ireport.gui.MainFrame.getMainInstance();
		}
		JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void alert(String message){
		showWarnMessageDialog(null, message, "警告");
	}
	public static void alertIfNull(String checkStr,String message){
		if(IreportUtil.isBlank(checkStr)){
		showWarnMessageDialog(null, message, "警告");
		}
	}
	
}

//end DialogFactory.java