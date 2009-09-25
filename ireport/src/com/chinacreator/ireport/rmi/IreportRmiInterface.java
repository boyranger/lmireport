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
import java.util.List;

/**
 * @author 李茂
 * @since 3.0
 * @version $Id: IreportRmiInterface.java 2009-8-19 上午08:47:03 $
 *
 */
//begin IreportRmiInterface.java
public interface IreportRmiInterface extends Remote{
	//测试方法
	int add(int a,int b)throws RemoteException;

	//保存过程
	void save(IreportFile ireportFile)throws RemoteException;

	//打开远程文件
	IreportFile open(IreportSession session,String fileName)throws RemoteException;

	//获取数据源列表
	String getDataSourceList()throws RemoteException;

	List<IreportFile> getAllPlugins() throws RemoteException;

	/**
	 * 获得所有模板信息
	 * @return
	 * @throws RemoteException
	 */
	List<IreportFile> getAllTemplates() throws RemoteException;

	/**
	 * 解除服务器端对应报表ID的锁
	 * @param 当前客户端IP
	 * @param repId
	 * @return
	 */
	String unLockReport(String ip,String repId)throws RemoteException;

	/**
	 * 获取该报表对应的最新锁定记录
	 * @param repid
	 * @return
	 * @throws RemoteException
	 */
	ReportLock getCurrentLock(String repid) throws RemoteException;

	/**
	 *	锁定报表
	 * @return
	 */
	boolean lockReport(ReportLock rl) throws RemoteException;

	/**
	 * 将客户端新建的文件保存到服务器
	 * @param ife
	 * @return
	 */
	String addNewReport(IreportFile ife)throws RemoteException;

	/**
	 * 执行服务器端的某个方法
	 * @param businessCode 自定义某值对应某方法业务操作完全由自己定义
	 * @return
	 */
	Object invokeServerMethod(int businessCode,Object... obj)throws RemoteException;

	/**
	 * 一些需要的同步到客户端的jar包
	 * @return
	 * @throws RemoteException
	 */
	Object synchronizationLibJar()throws RemoteException;

	/**
	 *
	 * @param ifi
	 * @param cilentPath
	 * @return
	 * @throws RemoteException
	 */
	Object sendFileToClient(String serverFileNamePath)throws RemoteException;

	/**
	 * 保存模板文件，包括模板xml文件和模板预览图片
	 * @param tf 模板文件对象
	 * @return 
	 * @throws RemoteException
	 */
	Object saveTemplatesFile(TemplateFiles tf,boolean editor)throws RemoteException;
}

//end IreportRmiInterface.java