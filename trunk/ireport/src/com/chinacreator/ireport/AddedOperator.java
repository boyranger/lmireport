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

import it.businesslogic.ireport.IReportConnection;
import it.businesslogic.ireport.Report;
import it.businesslogic.ireport.gui.MainFrame;
import it.businesslogic.ireport.util.I18n;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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




	public Object addRemotDatasource() {
		String xmlString = "<?xml version=\"1.0\"?>" +
							"<iReportConnectionSet>	" +
							"<iReportConnection name=\"mysql\" connectionClass=\"it.businesslogic.ireport.connection.JDBCConnection\">" +
							"<connectionParameter name=\"ServerAddress\"><![CDATA[localhost]]></connectionParameter>" +
							"<connectionParameter name=\"SavePassword\"><![CDATA[true]]></connectionParameter>"+
							"<connectionParameter name=\"Url\"><![CDATA[jdbc:mysql://localhost/openi]]></connectionParameter>"+
							"<connectionParameter name=\"JDBCDriver\"><![CDATA[com.mysql.jdbc.Driver]]></connectionParameter>"+
							"<connectionParameter name=\"Database\"><![CDATA[openi]]></connectionParameter>"+
							"<connectionParameter name=\"Password\"><![CDATA[123456]]></connectionParameter>"+
							"<connectionParameter name=\"Username\"><![CDATA[root]]></connectionParameter>"+
							"</iReportConnection>"+
							"</iReportConnectionSet>";
		//����.....
		//1:ɾ������Զ������
		Vector conns = MainFrame.getMainInstance().getConnections();

		if(conns != null){
		for (int i = 0; i < conns.size(); i++) {
			IReportConnection irc = (IReportConnection)conns.elementAt(i);

			//��������Զ��������ɾ����������Դ
			if(irc.getName().endsWith(IreportConstant.REMOTE_SUFFIX)){
				System.out.println("�Ƴ�:"+irc.getName());
				MainFrame.getMainInstance().getConnections().removeElement(irc);
			}
		}
		}
		//2:��������Զ������Դ
		   if(IreportUtil.isBlank(xmlString)){
			   return null;
		   }

	         try {
	             DOMParser parser = new DOMParser();
	             org.xml.sax.InputSource input_sss  = new org.xml.sax.InputSource(new ByteArrayInputStream(xmlString.getBytes()));
	             parser.parse( input_sss );

	             Document document = parser.getDocument();
	             Node node = document.getDocumentElement();


	             NodeList list_child = node.getChildNodes(); // The root is iReportConnections
	             for (int ck=0; ck< list_child.getLength(); ck++) {
	                 Node connectionNode = (Node)list_child.item(ck);
	                 if (connectionNode.getNodeName() != null && connectionNode.getNodeName().equals("iReportConnection"))
	                 {
	                    // Take the CDATA...
	                        String connectionName = "";
	                        String connectionClass = "";
	                        HashMap hm = new HashMap();
	                        NamedNodeMap nnm = connectionNode.getAttributes();
	                        if ( nnm.getNamedItem("name") != null) connectionName = nnm.getNamedItem("name").getNodeValue();
	                        if ( nnm.getNamedItem("connectionClass") != null) connectionClass = nnm.getNamedItem("connectionClass").getNodeValue();

	                        // Get all connections parameters...
	                        NodeList list_child2 = connectionNode.getChildNodes();
	                        for (int ck2=0; ck2< list_child2.getLength(); ck2++) {
	                            String parameterName = "";
	                            Node child_child = (Node)list_child2.item(ck2);
	                            if (child_child.getNodeType() == Node.ELEMENT_NODE && child_child.getNodeName().equals("connectionParameter")) {

	                                NamedNodeMap nnm2 = child_child.getAttributes();
	                                if ( nnm2.getNamedItem("name") != null)
	                                    parameterName = nnm2.getNamedItem("name").getNodeValue();
	                                hm.put( parameterName,Report.readPCDATA(child_child));
	                            }
	                        }

	                        // ������ִ��ڣ�������Ϊ "name (2)"
	                        //Զ������Դ�ڵ�һ����ȫ��ɾ����Ȼ���ڼ���Զ�̺�׺���ǲ������ظ���
	                        //connectionName = ConnectionsDialog.getAvailableConnectionName(connectionName);
	                        connectionName +=IreportConstant.REMOTE_SUFFIX; //����Զ������Դ��Ҫ��Ӻ�׺��ʶ
	                        try {
	                            IReportConnection con = (IReportConnection) Class.forName(connectionClass).newInstance();
	                            con.loadProperties(hm);
	                            con.setName(connectionName);

	                            MainFrame.getMainInstance().getConnections().add(con);
	                            //����Ĭ��ѡ��
	                            if(IreportConstant.DEFAULT_DATASOURCE_NAME.equals(connectionName)){
	                                MainFrame.getMainInstance().setActiveConnection(con);
	                            }

	                        } catch (Exception ex) {

	                            JOptionPane.showMessageDialog(MainFrame.getMainInstance(),
	                                I18n.getFormattedString("messages.connectionsDialog.errorLoadingConnection" ,"Error loading  {0}", new Object[]{connectionName}),
	                                I18n.getString("message.title.error","Error"), JOptionPane.ERROR_MESSAGE);
	                        }
	                }
	             }
	         } catch (Exception ex)
	         {
	             JOptionPane.showMessageDialog(MainFrame.getMainInstance(),
	                                I18n.getFormattedString("messages.connectionsDialog.errorLoadingConnections" ,"Error loading connections.\n{0}", new Object[]{ex.getMessage()}),
	                                I18n.getString("message.title.error","Error"), JOptionPane.ERROR_MESSAGE);
	              ex.printStackTrace();
	         }

	         MainFrame.getMainInstance().saveiReportConfiguration();
		return null;
	}

	public Object openRemoteFile(String fileName) {
		if(IreportUtil.isBlank(fileName)){
			//return null;
			fileName = "fgh.jrxml"; //����
		}
		  IreportFile ireportFile = null;
	        try {
	        	ireportFile = IreportRmiClient.getInstance().rmiInterfactRemote.open(fileName);

			if(ireportFile != null){
				String path = "G:\\z\\"+ireportFile.getFileName();
				File oldFile = new File(path);
				if(oldFile.exists()){
					oldFile.delete();
				}

				byte[] content = ireportFile.getContent();
			    File f = IreportUtil.bytesToFile(path, content);

			    return f;
			  }
	        } catch (Exception e) {
				e.printStackTrace();
			}
		return null;
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
	public static void main(String[] args) {
		getInstance().addRemotDatasource();
	}
}

//end AddedOperator.java