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

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.UIManager;

import com.chinacreator.ireport.component.DialogFactory;
import com.chinacreator.ireport.rmi.IreportFile;
import com.chinacreator.ireport.rmi.IreportRmiClient;

/**
 * @author ��ï
 * @since 3.0
 * @version $Id: AddedOperator.java 2009-9-1 ����02:50:45 $
 *
 */
//begin AddedOperator.java

//���к�����ӵĲ������������������
public class AddedOperator implements AddedOepretorInterface{

	public static AddedOepretorInterface addInstance = null;
	public Object afterClose() {
		//log.debug("************��ʼִ��afterClose***********");
		//log.debug("************����ִ��afterClose***********");
		return null;
	}

	public Object afterOpen() {
		// FIXME Auto-generated method stub
		return null;
	}

	//�������ı����������Ҫ������Ĵ�����Ҫ���Խ�����ļ����浽������
	public Object afterSave(String saveFilePath) {
		 System.out.println("************��ʼִ��afterSave***********");
		 try {
	           String filePath =  saveFilePath;
	           File f = new File(filePath);
	           IreportFile rf = new IreportFile();
	           rf.setFileName(f.getName());

	           byte[] content = new byte[(int)f.length()];
	           BufferedInputStream input = new BufferedInputStream(new FileInputStream(f));
	           input.read(content);
	           if(input != null ){
		              try{
		                  input.close();
		                  input = null ;
		              }catch(Exception ex){
		              }
		           }
	           rf.setContent(content);
	           IreportRmiClient.getInstance();
	           IreportRmiClient.rmiInterfactRemote.save(rf);
	            } catch (Exception e) {
					e.printStackTrace();
					DialogFactory.showErrorMessageDialog(null, "�ڽ��з������ļ�����ʱ�����쳣:"+e.getMessage(), "������ʾ");

		}
	   System.out.println("************����ִ��afterSave***********");
		return null;
	}

	public Object afterSaveAll() {
		// FIXME Auto-generated method stub
		return null;
	}

	public Object beforeClose() {
		// FIXME Auto-generated method stub
		return null;
	}

	public Object beforeOpen() {
		// FIXME Auto-generated method stub
		return null;
	}

	public Object beforeSave() {
		// FIXME Auto-generated method stub
		return null;
	}

	public Object beforeSaveAll() {
		// FIXME Auto-generated method stub
		return null;
	}

	//����Ҫͬ����
	public static AddedOepretorInterface getInstance(){
		if(addInstance == null){
			addInstance =  new AddedOperator();
		}
		return addInstance;
	}

	//swing ��Ҫע��������ܲ�����չʾʱ��������
	public Object registerSongTi() {
		System.out.println("ע������");
		try{
		Font font =  new Font("����",Font.PLAIN,12);
    	UIManager.put("Button.font",font);
    	UIManager.put("ToggleButton.font",font);
    	UIManager.put("RadioButton.font",font);
    	UIManager.put("CheckBox.font",font);
    	UIManager.put("ColorChooser.font",font);
    	UIManager.put("ToggleButton.font",font);
    	UIManager.put("ComboBox.font",font);
    	UIManager.put("ComboBoxItem.font",font);
    	UIManager.put("InternalFrame.titleFont",font);
    	UIManager.put("Label.font",font);
    	UIManager.put("List.font",font);
    	UIManager.put("MenuBar.font",font);
    	UIManager.put("Menu.font",font);
    	UIManager.put("MenuItem.font",font);
    	UIManager.put("RadioButtonMenuItem.font",font);
    	UIManager.put("CheckBoxMenuItem.font",font);
    	UIManager.put("PopupMenu.font",font);
    	UIManager.put("OptionPane.font",font);

    	UIManager.put("ProgressBar.font",font);
    	UIManager.put("ScrollPane.font",font);
    	UIManager.put("Viewport",font);
    	UIManager.put("TabbedPane.font",font);
    	UIManager.put("TableHeader.font",font);
    	UIManager.put("Table.font",font);
    	UIManager.put("TextField.font",font);
    	UIManager.put("PasswordFiled.font",font);
    	UIManager.put("TextArea.font",font);
    	UIManager.put("TextPane.font",font);
    	UIManager.put("EditorPane.font",font);
    	UIManager.put("TitledBorder.font",font);
    	UIManager.put("ToolBar.font",font);
    	UIManager.put("ToolTip.font",font);
    	UIManager.put("Tree.font",font);

    	UIManager.put("ComboBox", font);
    	UIManager.put("ComboBox.font", font);
    	UIManager.put("JComboBox.font", font);
    	UIManager.put("JComboBox", font);
    	UIManager.put("JTextField", font);
    	} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}

//end AddedOperator.java