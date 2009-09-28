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

import java.rmi.Naming;

import com.chinacreator.ireport.AddedOperator;
import com.chinacreator.ireport.IreportConstant;
import com.chinacreator.ireport.IreportUtil;
import com.chinacreator.ireport.MyReportProperties;

/**
 * @author 李茂
 * @since 3.0
 * @version $Id: IreportRmiClient.java 2009-8-19 上午08:53:02 $
 *
 */
//begin IreportRmiClient.java
public class IreportRmiClient {
	private static IreportRmiClient client = null;
	public static IreportRmiInterface rmiInterfactRemote = null;
	private static String ip = null;
	private static String port = null;

	private static boolean flag = true;

	public static synchronized IreportRmiClient getInstance (){
		try {
		if(
				//true ||
			 client == null){ //永远执行
			 ip = MyReportProperties.getStringProperties(IreportConstant.RMI_IP);//由外部传入
			 port =  MyReportProperties.getStringProperties(IreportConstant.RMI_PORT); //由外部传入
			 if(IreportUtil.isBlank(ip)){
				 ip="127.0.0.1";

				 System.out.println("初始化未找到IP，使用本地IP：127.0.0.1");
			 }
			 if(IreportUtil.isBlank(port)){
				 port="10086";
				 System.out.println("初始化未找到PORT，使用PORT：10086");
			 }
			rmiInterfactRemote = (IreportRmiInterface) Naming.lookup("rmi://"+ip+":"+port+"/ireportRmiServer");
			client = new IreportRmiClient();
			if(client!=null){
				System.out.println("》》》》》》创建客户端RMI连接，IP："+ip+"端口："+port);
			}
			if(flag){
				//AddedOperator.log("创建于服务器的RMI连接[IP:"+ip+",PORT:"+port+"]成功", IreportConstant.RIGHT_);
				flag = false;
			}
		}
		} catch (Exception e) {
			//AddedOperator.log("创建于服务器的RMI连接[IP:"+ip+",PORT:"+port+"]失败，你的所有远程操作将不能进行,由于："+e.getMessage(), IreportConstant.ERROR_);
			//e.printStackTrace();
			System.err.println(e.getMessage());
			flag = true;
		}
		return client;
	}

	public static IreportRmiInterface getRmiRemoteInterface(){


		getInstance();
		return rmiInterfactRemote;

	}

	public static void main(String[] args) {
/*		System.getProperties().put("proxySet","true");
		System.getProperties().put("proxyHost","192.168.11.110");
		System.getProperties().put("proxyPort","1070");*/

	try {
		MyReportProperties.setProperties(IreportConstant.RMI_IP, "192.168.11.118");
		MyReportProperties.setProperties(IreportConstant.RMI_PORT, "10086");

		int i = getRmiRemoteInterface().add(1, 2);

		System.out.println(i);
	} catch (Exception e) {
		e.printStackTrace();
	}

	}

}

//end IreportRmiClient.java