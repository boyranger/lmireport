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

import java.io.Serializable;

/**
 * @author 李茂
 * @since 3.0
 * @version $Id: RreportFormClass.java 2009-9-11 下午03:55:58 $
 *
 */
//begin RreportFormClass.java
public class ReportFormClass implements Serializable {

	private String app_id;

	private String ec_id;

	private String ec_name;

	private String ec_upid;

	private String remark;

	private int owner_id;

	private int ec_sn;

	/**
	 * @return app_id
	 */
	public String getApp_id() {
		return app_id;
	}

	/**
	 * @param app_id 要设置的 app_id
	 */
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	/**
	 * @return ec_id
	 */
	public String getEc_id() {
		return ec_id;
	}

	/**
	 * @param ec_id 要设置的 ec_id
	 */
	public void setEc_id(String ec_id) {
		this.ec_id = ec_id;
	}

	/**
	 * @return ec_name
	 */
	public String getEc_name() {
		return ec_name;
	}

	/**
	 * @param ec_name 要设置的 ec_name
	 */
	public void setEc_name(String ec_name) {
		this.ec_name = ec_name;
	}

	/**
	 * @return ec_sn
	 */
	public int getEc_sn() {
		return ec_sn;
	}

	/**
	 * @param ec_sn 要设置的 ec_sn
	 */
	public void setEc_sn(int ec_sn) {
		this.ec_sn = ec_sn;
	}

	/**
	 * @return ec_upid
	 */
	public String getEc_upid() {
		return ec_upid;
	}

	/**
	 * @param ec_upid 要设置的 ec_upid
	 */
	public void setEc_upid(String ec_upid) {
		this.ec_upid = ec_upid;
	}

	/**
	 * @return owner_id
	 */
	public int getOwner_id() {
		return owner_id;
	}

	/**
	 * @param owner_id 要设置的 owner_id
	 */
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	/**
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark 要设置的 remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String toString(){
		return this.ec_name;
	}
}

//end RreportFormClass.java