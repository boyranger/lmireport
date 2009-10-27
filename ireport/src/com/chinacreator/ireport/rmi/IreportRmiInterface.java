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

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

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

	/**
	 * 将一个报表文件保存到服务器
	 * @param ireportFile
	 * @throws RemoteException
	 */
	void save(IreportFile ireportFile)throws RemoteException;

	/**
	 * 打开服务器端报表文件
	 * @param session
	 * @param fileName
	 * @return
	 * @throws RemoteException
	 */
	IreportFile open(IreportSession session,String fileName)throws RemoteException;

	/**
	 * 获得远程服务器数据源表达式的xml字符串
	 * @return
	 * @throws RemoteException
	 */
	String getDataSourceList()throws RemoteException;

	/**
	 * 获得服务器端得所有插件信息
	 * @return
	 * @throws RemoteException
	 */
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
	 * 解除所有锁定记录
	 * @throws RemoteException 若删除信息出现异常
	 */
	void unLockAllReport()throws RemoteException;
	
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
	 * 发送一个服务器端文件到客户端
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

	/**
	 * 删除服务器端模板文件
	 * @param tf 模板文件
	 * @return
	 * @throws RemoteException
	 */
	Object deleteTemplateFile(TemplateFiles tf)throws RemoteException;
	
	/**
	 * 链路检测
	 * @return
	 * @throws RemoteException
	 */
	boolean ping()throws RemoteException;
	
	/**
	 * 获得服务器
	 * @param path    该值为基于某服务器应用的文件夹 比如 /creatorepp/report/lib
	 * 			      若需要取得lib目录下的文件列表，path的值应该等于/report/lib 或者 report/lib
	 * @param allowed 允许通过的文件类型，其他的都过滤，若为空表示不做处理
	 * @return
	 * @throws RemoteException
	 */
	File[] getServerFileList(String path,String[] allowed)throws RemoteException;

	/**
	 * 获得服务器端索引信息
	 * @return
	 * @throws RemoteException
	 */
	IndexInfo indexInfo() throws RemoteException;
	
	/**
	 * 生成该引用下的classes目录文件索引
	 * @return 
	 * @throws RemoteException
	 */
	boolean generateIndex() throws RemoteException;
	
	/**
	 * 查询索引数据
	 * @param condition 查询条件
	 * @return
	 * @throws RemoteException
	 */
	 List<String>  searchIndex(String condition) throws RemoteException;
	
	/**
	 * 获得服务器端索引进度
	 * @return 整数数组，第一个为总共需要的统计数，第二个为现已索引完毕的数目
	 * @throws RemoteException
	 */
	int[] indexProgress() throws RemoteException;
	
	/**
	 * 查询该类的所有方法
	 * @return
	 * @throws RemoteException
	 */
	String[] searchClassMethods(String fullClassName) throws RemoteException;
	
	/**
	 * 执行javabean数据源中的方法，默认情况下是要求该方法是静态方法
	 * @param fullClassName
	 * @param methodName
	 * @return
	 */
	boolean invokeJavaBeanMethod(String fullClassName,String methodName,Object[] obj) throws RemoteException;

	/**
	 * 获得服务器端某类的属性描述集
	 * @param classname
	 * @return
	 * @throws RemoteException
	 */
	List<RemoteBeanPropertyDescriptor> getRemoteBeanProperty(String classname)throws RemoteException;
	
	/**
	 * 执行服务端的某个类的某个方法获得一个数据集，该数据集满足ireportjavabean数据集的要求，要么是collection要么是object数组
	 * 但是返回值需要进行处理，因为原生的返回值是服务器端某javabean的集合，而这个javabean是存在于服务器端的，在ireport客户端是
	 * 不存在的，所以需要将javabean转换为普通的map集合，在默认情况下我们保存对应javabean中的值与map中的值相等，但是请注意，javabean
	 * 中某属性的值也是也是一个不存在与ireport设计器客户端的javabean对象，所以这里我们将默认返回服务器端该对象toString方法返回的对象
	 * 字符串。
	 * @param className
	 * @param methodName
	 * @return
	 * @throws RemoteException
	 */
	List<Map<String,Object>> remoteBeanCollectionDataset(String className,String methodName,int collectionType)throws RemoteException;

	/**
	 * 添加一条javabean数据源记录
	 * @param rdb
	 * @throws RemoteException
	 */
	void addJavaBeanDataSourceRecord(ReportDatasourceBean rdb) throws RemoteException;

	/**
	 * 删除一条javabean数据源记录
	 * @param repid
	 */
	void deleteJavaBeanDataSourceRecord(String repid)throws RemoteException;
	
	/**
	 * 修改一条javabean数据源记录
	 * @param rdb
	 * @throws RemoteException
	 */
	void updateJavaBeanDataSourceRecord(ReportDatasourceBean rdb)throws RemoteException;
}

//end IreportRmiInterface.java