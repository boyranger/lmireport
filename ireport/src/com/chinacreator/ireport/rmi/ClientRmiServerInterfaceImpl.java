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
package com.chinacreator.ireport.rmi;

import it.businesslogic.ireport.gui.JReportFrame;
import it.businesslogic.ireport.gui.MainFrame;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import com.chinacreator.ireport.IreportUtil;
import com.chinacreator.ireport.component.DialogFactory;

/**
 * @author 李茂
 * @since 3.0
 * @version $Id: ClientRmiServerInterfaceImpl.java 2009-9-7 上午10:09:51 $
 *
 */
//begin ClientRmiServerInterfaceImpl.java
public class ClientRmiServerInterfaceImpl extends UnicastRemoteObject implements
		ClientRmiServerInterface {

	private static ClientRmiServerInterfaceImpl crm= null;

	public static ClientRmiServerInterfaceImpl getInstance(){
		if(crm == null){
			try {
				crm = new ClientRmiServerInterfaceImpl();
			} catch (RemoteException e) {
				// FIXME Auto-generated catch block
				e.printStackTrace();
			}
		}
		return crm;
	}
	public ClientRmiServerInterfaceImpl() throws RemoteException{
		super();
	}
	public boolean linkCheck() throws RemoteException{
		return true;
	}

	public Object openFile(final IreportSession session,final IreportFile file) throws RemoteException{

		//new Thread(new Runnable(){
			//public void run() {

			      try {
			    	  File f = new File(MainFrame.getMainInstance().IREPORT_TMP_FILE_DIR+File.separator+file.getFileName());
			    	  if(f.exists()){
			        	  f.delete();
			          }
			    	  File remoteFile = IreportUtil.bytesToFile(MainFrame.getMainInstance().IREPORT_TMP_FILE_DIR+File.separator+file.getFileName(), file.getContent());
			    	  if(!remoteFile.exists()){
			    		  remoteFile.createNewFile();
			    	  }
			    	  System.out.println("远程文件保存在:"+remoteFile.getPath());
			    	  System.out.println("远程文件大小为:"+ file.getContent().length);
			    	  JReportFrame jrf = MainFrame.getMainInstance().openFile( remoteFile );
			          IreportRmiClient.getInstance();
			          boolean b = IreportRmiClient.getRmiRemoteInterface().lockReport(IreportUtil.getReportLock(IreportUtil.getIdFromReport(file.getFileName())));
			          if(!b){
			        	  DialogFactory.showErrorMessageDialog(null, "锁定服务器端文件出错，你的后续修改将对服务器文件无效", "锁定错误");
			          }
			          setVisible(true);
			          jrf.setSelected(true);

			      } catch (Exception ex){
			          ex.printStackTrace();
			      }

			//}

		//}).start();

		return null;
	}

	  public boolean setVisible(boolean b)
	  {
	      MainFrame.getMainInstance().setVisible(b);
	      if (MainFrame.getMainInstance().getState() == java.awt.Frame.ICONIFIED)
	      {
	            MainFrame.getMainInstance().setState( java.awt.Frame.NORMAL );
	      }
	      return MainFrame.getMainInstance().requestFocusInWindow();
	  }
	public Object openCilentDialog(String text,String title, int messageType) {
		JOptionPane.showMessageDialog(MainFrame.getMainInstance(), text, title, messageType);
		return null;
	}
}

//end ClientRmiServerInterfaceImpl.java