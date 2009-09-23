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
import it.businesslogic.ireport.gui.JReportFrame;
import it.businesslogic.ireport.gui.MainFrame;
import it.businesslogic.ireport.util.I18n;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.chinacreator.ireport.component.DialogFactory;
import com.chinacreator.ireport.rmi.IreportFile;
import com.chinacreator.ireport.rmi.IreportRmiClient;
import com.chinacreator.ireport.rmi.IreportSession;
import com.chinacreator.ireport.rmi.ReportLock;

/**
 * @author 李茂
 * @since 3.0
 * @version $Id: AddedOperator.java 2009-9-1 下午02:50:45 $
 *
 */
//begin AddedOperator.java

//所有后期添加的操作方法都从这里调用
public class AddedOperator implements AddedOepretorInterface{
	private static Log logger = LogFactory.getLog(AddedOperator.class);
	public static AddedOepretorInterface addInstance = null;
	public Object afterClose(String closeFilePath) {
		logger.info("**********开始afterClose***********");
		String id = null;
		String realId = null;
		try {
			//判断是不是由本地新建的文件
			 String localRealFileId = IreportUtil.isLocalNewToServerFile(closeFilePath);

			 IreportRmiClient.getInstance();
			 File f = new File(closeFilePath);
			 String repid = IreportUtil.getIdFromReport(f.getName());

			 realId = repid;
			 if(IreportUtil.isBlank(repid)){
				 log("关闭时取到的报表ID为空", IreportConstant.ERROR_);
				 DialogFactory.showErrorMessageDialog(null, "关闭时取到的报表ID为空！", "错误");
			 }


			 if(!IreportUtil.isBlank(localRealFileId)){
				//满足条件判断为由本地新建的文件
				 realId = localRealFileId;
			 }

			  id = repid;//这是本地看到名字
	          IreportRmiClient.rmiInterfactRemote.unLockReport(IreportUtil.getLocalIp(),IreportUtil.removeNameSuffix(realId));

	          //清除本地锁定信息
	         IreportUtil.removeLocalToServerLock(closeFilePath, false);
		} catch (Exception e) {
			e.printStackTrace();
			log("关闭时取消["+id+"]锁定时出错，你可能要手动解除锁定！\n"+e.getMessage(), IreportConstant.ERROR_);
			DialogFactory.showErrorMessageDialog(null, "关闭时取消["+id+"]锁定时出错，你可能要手动解除锁定！\n"+e.getMessage(), "错误");
		}
		logger.info("结束afterClose");
		return null;
	}

	public Object afterOpen() {
		// FIXME Auto-generated method stub
		return null;
	}

	//在正常的保存后我们需要做额外的处理，需要尝试将这个文件保存到服务器
	public Object afterSave(final String saveFilePath) {
		new Thread(new Runnable(){
		public void run() {
		logger.info("************开始执行afterSave***********");
		 try {
			   IreportRmiClient.getInstance();
			   String repid = IreportUtil.getIdFromReportPath(saveFilePath);
			   String localRealFileId = IreportUtil.isLocalNewToServerFile(saveFilePath);
			   if(IreportUtil.isBlank(localRealFileId)){//不是本地新建的文件
			   ReportLock r = IreportRmiClient.rmiInterfactRemote.getCurrentLock(IreportUtil.removeNameSuffix(repid));

			   //若当前该报表的锁不是你的，你是不允许保存操作的
			   if(!IreportUtil.isYourLock(r)){
				   log("服务器该文件锁不属于你控制，你不能进行服务器保存操作", IreportConstant.ERROR_);
				   DialogFactory.showErrorMessageDialog(null, "服务器该文件锁不属于你控制，你不能进行服务器保存操作", "错误提示");
				   return;
			   }
			   }
	           String filePath =  saveFilePath;
	           File f = new File(filePath);
	           IreportFile rf = new IreportFile();
	           rf.setFileName(IreportUtil.removeNameSuffix(f.getName()));

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

	           //本地新建到服务器的文件
	           if(!IreportUtil.isBlank(localRealFileId)){
	        	   rf.setFileName(localRealFileId+".jrxml");
	           }
	           rf.setContent(content);

	           IreportRmiClient.rmiInterfactRemote.save(rf);
	            } catch (Exception e) {
					e.printStackTrace();
					log("服务器文件保存失败", IreportConstant.ERROR_);
					DialogFactory.showErrorMessageDialog(null, "在进行服务器文件保存时出现异常:"+e.getMessage(), "错误提示");

		}
	        	}

	    		}).start();
	            logger.info("************结束执行afterSave***********");
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

	//不需要同步锁
	public static AddedOepretorInterface getInstance(){

		if(addInstance == null){
			addInstance =  new AddedOperator();
		}

		return addInstance;
	}




	public Object addRemotDatasource() {

		logger.info("添加远程数据源信息addRemotDatasource");
		//new Thread(new Runnable(){
			//public void run() {

		IReportConnection myDefaulconn = null;
				try {
					 IreportRmiClient.getInstance();
					 String xmlString = IreportRmiClient.rmiInterfactRemote.getDataSourceList();
					//步骤.....
					//1:删除已有远程连接
					 Vector conns = (Vector) MainFrame.getMainInstance().getConnections().clone();
					if(conns != null){
					for (int i = 0; i < conns.size(); i++) {
						IReportConnection irc = (IReportConnection)conns.elementAt(i);
						//若是满足远程条件将删除本地数据源

						if(irc.getName().endsWith(IreportConstant.REMOTE_SUFFIX)){
							MainFrame.getMainInstance().getConnections().removeElement(irc);
							//MainFrame.getMainInstance().getConnections().r
						}
					}
					}
					//2:解析已有远程数据源
					   if(IreportUtil.isBlank(xmlString)){
						   return null;
					   }

				             DOMParser parser = new DOMParser();
				             org.xml.sax.InputSource input_sss  = new org.xml.sax.InputSource(new ByteArrayInputStream(xmlString.getBytes()));
				             parser.parse( input_sss );

				             Document document = parser.getDocument();
				             Node node = document.getDocumentElement();


				             NodeList list_child = node.getChildNodes(); // The root is iReportConnections
				             int dataSourceCount = 0;
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

				                        // 如果名字存在，重命名为 "name (2)"
				                        //远程数据源在第一步骤全部删除，然后在加上远程后缀后是不可能重复的
				                        //connectionName = ConnectionsDialog.getAvailableConnectionName(connectionName);
				                        connectionName +=IreportConstant.REMOTE_SUFFIX; //保存远程数据源需要添加后缀标识
				                        try {
				                            IReportConnection con = (IReportConnection) Class.forName(connectionClass).newInstance();
				                            con.loadProperties(hm);
				                            con.setName(connectionName);

				                            MainFrame.getMainInstance().getConnections().add(con);
				                            //设置默认选择
				                            if(IreportConstant.DEFAULT_DATASOURCE_NAME.equals(connectionName)){
				                            	myDefaulconn = con;
				                            	MainFrame.getMainInstance().setActiveConnection(myDefaulconn);
				                            }

				                        } catch (Exception ex) {

				                            JOptionPane.showMessageDialog(MainFrame.getMainInstance(),
				                                I18n.getFormattedString("messages.connectionsDialog.errorLoadingConnection" ,"Error loading  {0}", new Object[]{connectionName}),
				                                I18n.getString("message.title.error","Error"), JOptionPane.ERROR_MESSAGE);
				                        }
				                        dataSourceCount++;
				                }
				             }

				             log("成功加载服务器数据源"+dataSourceCount+"个", JOptionPane.INFORMATION_MESSAGE);
				         } catch (Exception ex)
				         {
				        	 log("加载远程数据源失败", JOptionPane.ERROR_MESSAGE);
				        	 /*  JOptionPane.showMessageDialog(MainFrame.getMainInstance(),
				                                I18n.getFormattedString("messages.connectionsDialog.errorLoadingConnections" ,"Error loading connections.\n{0}", new Object[]{ex.getMessage()}),
				                                I18n.getString("message.title.error","Error"), JOptionPane.ERROR_MESSAGE);
				              ex.printStackTrace();*/
				         }
				         MainFrame.getMainInstance().setActiveConnection(myDefaulconn);
				         MainFrame.getMainInstance().saveiReportConfiguration();

			//}

		//}).start();
		logger.info("结束addRemotDatasource");
		return null;
	}

	public Object openRemoteFile() {
		logger.info("开始openRemoteFile");
		try {
			String fileName = MyReportProperties.getStringProperties(IreportConstant.REPORT_ID);
			if(IreportUtil.isBlank(fileName)){
			logger.warn(">>>>>>>>>>未找到远程服务器文件");
				return null;
			}
			if(!fileName.toLowerCase().endsWith(".jrxml")){
				fileName+=".jrxml";
			}
			logger.info("=============尝试打开远程服务器文件"+fileName+"==========");

		    IreportFile ireportFile = null;

	        IreportSession session = new IreportSession();
	        session.setIp(MyReportProperties.getStringProperties(IreportConstant.IP));
	        session.setRepid(fileName.split("\\.")[0]);
	        session.setUsername(MyReportProperties.getStringProperties(IreportConstant.USERNAME));

	        //可能存在打开的多个文件，都需要保存一份到本地，在保存的时候需要用到
	        MyReportProperties.setProperties(IreportConstant.SESSION_SUFFIX+session.getRepid(), session);
	        logger.info("开始执行远程调用open");
	        ireportFile = IreportRmiClient.getInstance().rmiInterfactRemote.open(session,fileName);
	        logger.info("结束执行远程调用open，获得为："+ireportFile);
	        if(ireportFile != null){
	        	logger.info("ireportFile为："+ireportFile.getFileName()+"::::"+ireportFile.getContent().length);
				String tmp_file_path = MainFrame.getMainInstance().IREPORT_TMP_FILE_DIR;
				File tmp_file = new File(tmp_file_path);
				if(!tmp_file.exists()){
					tmp_file.mkdirs();
				}
				String path = MainFrame.getMainInstance().IREPORT_TMP_FILE_DIR+"/"+IreportConstant.NAME_SUFFIX+ireportFile.getFileName();
				logger.info("报表文件夹存放于："+path);
				File oldFile = new File(path);
				if(oldFile.exists()){
					oldFile.delete();
				}

				byte[] content = ireportFile.getContent();
			    File f = IreportUtil.bytesToFile(path, content);
			    log("成功打开服务器文件:"+fileName, JOptionPane.INFORMATION_MESSAGE);
			    return f;
			  }else{
				  logger.warn(">>>>>>>>>>从服务器端获得的文件为空");
			  }
	        } catch (Exception e) {
	        	logger.error(e);
	        	log("打开远程报表文件失败。", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				//DialogFactory.showErrorMessageDialog(null, "打开远程报表文件失败！\n信息:"+e.getMessage() , "错误");
	        }
	        logger.info("结束openRemoteFile");
		return null;
	}

	//swing 需要注册宋体才能不会在展示时出现乱码
	public Object registerSongTi() {
		logger.info("开始registerSongTi");
		try{
		Font font =  new Font("宋体",Font.PLAIN,12);
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
    		logger.error(e);
			e.printStackTrace();
		}
    	logger.info("结束registerSongTi");
		return null;
	}


	public Object login(String serverApp, String username, String password) {
		if(IreportUtil.isBlank(serverApp) || IreportUtil.isBlank(username) || IreportUtil.isBlank(password)){
			return "必要信息未填写完整";
		}

		//开始login

		return IreportConstant.SUCCESS;
	}

	public Object logout() {

		return null;
	}


	public Object linkCheck() {
		// FIXME Auto-generated method stub
		return null;
	}

	public Object initTemplate() {
		logger.info("开始initTemplate");
			Thread initT = new Thread(new Runnable(){
				public void run() {
					try {
					System.out.println("开始加载模板文件夹："+MainFrame.getMainInstance().IREPORT_TMP_TEMPLATE_DIR);
					File tmplateDir = new File(MainFrame.getMainInstance().IREPORT_TMP_TEMPLATE_DIR);
					if(!tmplateDir.exists()){
						tmplateDir.mkdirs();
					}
					File[] fs = tmplateDir.listFiles();
					if(fs==null || fs.length==0){

						System.out.println("开始从服务器获取模板文件...");
						//删除本地模板文件
						for (int i = 0; i < fs.length; i++) {
							if(fs[i].isFile()){
								fs[i].delete();
							}
						}
						IreportRmiClient.getInstance();
						List<IreportFile> templates = IreportRmiClient.getRmiRemoteInterface().getAllTemplates();

						for (int i = 0; i < templates.size(); i++) {
							IreportUtil.bytesToFile(tmplateDir+"/"+templates.get(i).getFileName(), templates.get(i).getContent());
						}
						if(templates.size()==0){
							AddedOperator.log("未找到模板信息.", IreportConstant.WARN_);
						}else{
						AddedOperator.log("加载模板结束.", IreportConstant.INFO_);
						}
					}else{
						System.out.println("探测到本地已经存在模板文件，若需要同步服务器模板信息，需要手动操作！");
					}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			initT.setDaemon(true);
			initT.start();

		logger.info("结束initTemplate");
		return null;
	}

	public Object initRemoteArgs(String[] args) {
		logger.info("开始initRemoteArgs");

		System.out.println("args..............."+args.length);
		if(args!=null && args.length>0){
			for (int i = 0; i < args.length; i++) {
				System.out.println("********参数"+(i+1)+":["+args[i]+"]********");
				switch (i) {
				case 0: //第一参数为rmi_ip
					MyReportProperties.setProperties(IreportConstant.RMI_IP, args[i]);
					break;
				case 1: //第2参数为rmi_port
					MyReportProperties.setProperties(IreportConstant.RMI_PORT, args[i]);
					break;
				case 2: //第3参数为report_id
					MyReportProperties.setProperties(IreportConstant.REPORT_ID, args[i]);
					break;

				case 3: //第4参数为username
					MyReportProperties.setProperties(IreportConstant.USERNAME, args[i]);
					break;
				case 4: //第5参数为ip
					if(args[i]==null || "127.0.0.1".equals(args[i])){
						MyReportProperties.setProperties(IreportConstant.IP, IreportUtil.getLocalIp());
					}else{
						MyReportProperties.setProperties(IreportConstant.IP, args[i]);
					}
					break;
				}

		}
			}
		logger.info("结束initRemoteArgs");
		return null;
	}

	//先搜索本地文件系统再搜索服务器
	public Object initPluginsConfig() {
		logger.info("开始initPluginsConfig");
		try {
			File plugDir = new File(MainFrame.getMainInstance().IREPORT_PLUGIN_DIR);
			if (plugDir == null || !plugDir.exists() || plugDir.isFile()) {
				//没有找到配置文件目录将创建目录
				log("未找到插件文件夹："+plugDir.getPath()+"，创建该文件夹",IreportConstant.INFO_);
				System.out.println("未找到插件文件夹："+plugDir.getPath()+"，创建该文件夹");
				plugDir.mkdirs();
			}
			if(plugDir.listFiles()!=null && plugDir.listFiles().length==0){
				logger.info("从服务器加载插件文件...");
				//尝试从服务器端加载
				IreportRmiClient.getInstance();
				List<IreportFile>  fs = IreportRmiClient.rmiInterfactRemote.getAllPlugins();
		        if(fs==null){
		        	return null;
		        }
				for (int i = 0; i < fs.size(); i++) {
		        	IreportFile irf= fs.get(i);
		        	File f = IreportUtil.bytesToFile(MainFrame.getMainInstance().IREPORT_PLUGIN_DIR+"/"+irf.getFileName(), irf.getContent());
					f.mkdir();
					System.out.println("成功加载配置插件文件:"+f.getPath());
		        }
		        log("成功加载配置插件文件"+fs.size()+"个", IreportConstant.INFO_);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("结束initPluginsConfig");
		return null;
	}

	public Object beforeIreportLoadCheck() {
		logger.info("开始beforeIreportLoadCheck");
		boolean b = false;
		ServerSocket ss =null;
		try {
			 ss = new ServerSocket(Integer.parseInt(IreportConstant.CLIENT_RMI_PORT));
		} catch (Exception e) {
			//若有异常表示端口被占用，客户端将终止退出
			DialogFactory.showErrorMessageDialog(null, "端口"+IreportConstant.CLIENT_RMI_PORT+"已经被占用，你是不是已经启动了一个ireport实例?", "错误");
			b = true;
			e.printStackTrace();
		}finally{
			if(ss!=null){
				try {ss.close();} catch (IOException e) {}
			}
		}

		if(b){
			System.out.println("端口被占用，系统退出！");
			System.exit(0);
		}
		logger.info("结束beforeIreportLoadCheck");
		return null;
	}

	public Object beforeCloseApplication(JInternalFrame[] jif) {
		logger.info("开始beforeCloseApplication");
		if(jif == null){
			return null;
		}
		JReportFrame jf = null;
		boolean isError = false;
		String realId = null;
		String repid = null;

		for (int i = 0; i < jif.length; i++) {
			if(jif[i]!=null && jif[i] instanceof JReportFrame){

				jf = (JReportFrame) jif[i];
				String path = jf.getReport().getFilename();

				 repid = IreportUtil.getIdFromReportPath(path);

				if(IreportUtil.isRemoteFile(repid)){
				try {

					 String isLocal = MyReportProperties.getStringProperties(repid+IreportConstant.LOCAL_TO_SERVER);
					 if(!IreportUtil.isBlank(isLocal)){
						 realId = isLocal;
					 }else{
						 realId = repid;
					 }

					 String id = IreportRmiClient.rmiInterfactRemote.unLockReport(IreportUtil.getLocalIp(),IreportUtil.removeNameSuffix(realId));
					 IreportUtil.removeLocalToServerLock(repid,true);

					 if(IreportUtil.isBlank(id)){isError = true;}
				} catch (Exception e) {
					e.printStackTrace();
					isError = true;
				}
				}

			}
			if(isError){
				log("解除["+repid+"]锁定出错!",IreportConstant.ERROR_);
			}else{
				log("已经解除"+repid+".jrxml文件在服务器端的锁定", IreportConstant.RIGHT_);
			}
		}

		if(isError){
			DialogFactory.showErrorMessageDialog(null, "解除锁定文件出错!", "错误");

		}
		logger.info("结束beforeCloseApplication");
		return null;
	}

	public Object afterCloseJReportFrame(final JReportFrame jrf) {
		logger.info("开始afterCloseJReportFrame");
		//new Thread( new Runnable(){
			//public void run() {
				String errMsg = "";
				 String realId = null;
				if(jrf == null ){return null;}
				String path  = jrf.getReport().getFilename();
				String repid = IreportUtil.getIdFromReportPath(path);
				boolean isError = false;
				if(IreportUtil.isRemoteFile(repid)){
					try {

						 String isLocal = MyReportProperties.getStringProperties(repid+IreportConstant.LOCAL_TO_SERVER);
						 if(!IreportUtil.isBlank(isLocal)){
							 realId = isLocal;
						 }else{
							 realId = repid;
						 }
						 String id = IreportRmiClient.rmiInterfactRemote.unLockReport(IreportUtil.getLocalIp(),IreportUtil.removeNameSuffix(realId));
						 IreportUtil.removeLocalToServerLock(realId, true);
						 if(IreportUtil.isBlank(id)){isError = true;}
					} catch (Exception e) {
						errMsg = e.getMessage();
						e.printStackTrace();
						isError = true;
					}
					}
				if(isError){
					log("解除["+repid+"]锁定出错!\n"+errMsg,IreportConstant.ERROR_);
					DialogFactory.showErrorMessageDialog(null, "解除["+repid+"]锁定出错!\n"+errMsg, "错误");

				}else{
					if(IreportUtil.isRemoteFile(repid)){
					log("已经解除"+repid+".jrxml文件在服务器端的锁定", IreportConstant.RIGHT_);
					}
				}
		//	}

		//}).start();
		logger.info("结束afterCloseJReportFrame");
		return null;
	}

	public static void log(String text,int type){
		if(type==JOptionPane.WARNING_MESSAGE){
			type = IreportConstant.WARN_;
		}else if(type == JOptionPane.ERROR_MESSAGE){
			type = IreportConstant.ERROR_;
		}
		MainFrame.getMainInstance().getLogPane().getMainLogTextArea().logOnConsole(text, type);
	}

	public Object initLibJarFiles() {
		new Thread(new Runnable(){

			public void run() {
				try {
					String lib = MainFrame.IREPORT_USER_HOME_DIR+File.separator+"lib";
					File libDir = new File(lib);
					if(!libDir.exists()){
						libDir.mkdirs();
					}
					if(libDir!=null && libDir.isDirectory() && libDir.listFiles().length!=0){
						AddedOperator.log("探测到本地已经存在jar文件，不进行jar包服务器端同步", IreportConstant.INFO_);
						return;
					}
					IreportRmiClient.getInstance();
					List<IreportFile> list = (List<IreportFile>) IreportRmiClient.getRmiRemoteInterface().synchronizationLibJar();

					for (int i = 0; i < list.size(); i++) {
						File f = new File(lib,list.get(i).getFileName());
						if(!f.exists()){
						IreportUtil.bytesToFile(f.getPath(), list.get(i).getContent());
						AddedOperator.log("同步服务器jar包"+list.get(i).getFileName()+"到本地目录"+lib, IreportConstant.INFO_);
						}
					}
				} catch (Exception e) {

					e.printStackTrace();
					AddedOperator.log("同步服务器lib目录文件出错\n"+e.getMessage()+"\n"+e.getLocalizedMessage()+"\n"+e.getCause(),IreportConstant.ERROR_);
				}

			}

		}).start();

		return null;
	}
}

//end AddedOperator.java