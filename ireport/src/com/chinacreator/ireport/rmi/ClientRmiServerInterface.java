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

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author 李茂
 * @since 3.0
 * @version $Id: ClientRmiServerInterface.java 2009-9-7 上午09:54:40 $
 *
 */
//begin ClientRmiServerInterface.java
public interface ClientRmiServerInterface extends Remote{
	/**
	 * 链路检测，若可到达连接返回true否则false
	 * 场景：在服务端启动时可能需要使用到
	 * @return
	 */
	boolean linkCheck() throws RemoteException;

	/**
	 * 若客户端已经启动了一个ireport实例，当客户端尝试再次打开ireport时，将
	 * 被服务器器探知到，且不再在客户端打开新的ireport实例，而新打开的文件将通过
	 * 该命令在已经开启的ireport的实例中打开该文件
	 * @param file
	 * @return
	 */
	Object openFile(IreportSession session,IreportFile file) throws RemoteException;

	/**
	 * 打开客户端窗口
	 * @return
	 */
	Object openCilentDialog(String text,String title,int messageType)throws RemoteException;
}

//end ClientRmiServerInterface.java