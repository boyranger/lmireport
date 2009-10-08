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

import it.businesslogic.ireport.gui.JReportFrame;

import javax.swing.JInternalFrame;

/**
 * @author 李茂
 * @since 3.0
 * @version $Id: AddedOepretorInterface.java 2009-9-1 下午02:54:18 $
 *
 */
//begin AddedOepretorInterface.java
public interface AddedOepretorInterface {
	/**
	 * 打开某文件之前
	 * @return
	 */
	Object beforeOpen();

	/**
	 * 打开文件之后
	 * @return
	 */
	Object afterOpen();

	Object beforeSave();

	Object afterSave(String saveFilePath);

	Object beforeSaveAll();

	Object afterSaveAll();

	Object beforeClose();

	Object afterClose(String closeFilePath);

	Object registerSongTi();

	/**
	 * 在启动初始化完毕后，加载远程数据源，策略为：
	 * 保持本地连接不变，尝试在本地配置文件中查找是否有数据源，若没有直接添加所有远程数据源
	 * 若有，先删除本地数据源再添加所有远程数据源.
	 * @return
	 */
	Object addRemotDatasource();

	/**
	 * 在启动初始化完毕后，若已经传入了需要打开的远程文件名，将尝试打开远程文件
	 * @return
	 */
	Object openRemoteFile();

	/**
	 * 用户登陆
	 * @param serverApp 登陆应用 比如 "http://192.168.11.110:8080/app3"
	 * @param username 登陆用户名
	 * @return password 登陆密码
	 */
	Object login(String serverApp,String username,String password);

	/**
	 * 用户注销
	 * @return
	 */
	Object logout();

	/**
	 * 链路检测
	 * @return
	 */
	Object linkCheck();

	/**
	 * 初始服务器模板文件到客户端，初始化策略为
	 * 先查看服务器是端模板文件夹是否有模板文件，若没有尝试从服务器端获取
	 * 若有则不在初始化客户端的时候获取。但是提供额外的功能去同步服务器端的模板
	 * 该模板的初始化应该是异步的，成败不应该影响主程序的运行
	 * @return
	 */
	Object initTemplate();

	/**
	 * 从外部传递过来的参数...
	 * 注意顺序
	 * @param args
	 * @return
	 */
	Object initRemoteArgs(String[] args);

	/**
	 * 优先本地加载策略
	 * 插件配置文件的加载改为最初从服务器加载，且完成从服务器到本地的复制
	 * 每次启动将尝试读取本地文件配置是否含有插件配置信息，若没有将从服务器
	 * 端获取信息
	 * @return File[]
	 */
	Object initPluginsConfig();

	/**
	 * 在ireport加载的时候需要判断，客户端是否已经启用的一个ireport实例
	 * 若已经启动将不再启动实例，这种情况的存在在于客户端启动不是通过页面启动
	 * 的时候出现的，若是在页面启动，页面先会判断客户端是否已经启动ireport实例
	 * 若已经启动将不会从服务器加载ireport应用，而尝试直接连接客户端ireport。
	 * @return
	 */
	Object beforeIreportLoadCheck();

	/**
	 * 该方法是在用户关闭应用时回调的一个函数
	 * 该函数主要处理的业务逻辑为接触服务器端对应文件的锁定
	 * @param jif
	 * @return
	 */
	Object beforeCloseApplication(JInternalFrame[] jif);

	/**
	 * 在界面关闭某个JReportFrame调用的方法
	 * 他的效果相当于 ”close“
	 * 所以在完成这个动作时应该解除当前报表的锁定
	 * @param jrf
	 * @return
	 */
	Object afterCloseJReportFrame(JReportFrame jrf);

	/**
	 * 需要从服务器同步Lib目录jar文件
	 * @return
	 */
	Object initLibJarFiles();
	
	/**
	 * 增量同步服务器文件到本地
	 * @return
	 */
	Object incrementAddFile(int type);
	
}

//end AddedOepretorInterface.java