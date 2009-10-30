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
import java.util.Date;

/**
 * @author ¿Ó√Ø
 * @since 3.0
 * @version $Id: ReportLock.java 2009-9-8 …œŒÁ08:38:49 $
 *
 */
//begin ReportLock.java
public class ReportLock implements Serializable{

			private String lock_id;
			private String rep_id ;
			private Date open_time;
			private String open_user;
			private String rep_type;
			private String statues;
			private String rep_ref_file;
			private Date time_out_time;
			private String open_user_ip;
			private String rep_name;

			public String getLock_id() {
				return lock_id;
			}
			public void setLock_id(String lock_id) {
				this.lock_id = lock_id;
			}
			public String getRep_id() {
				return rep_id;
			}
			public void setRep_id(String rep_id) {
				this.rep_id = rep_id;
			}

			public Date getOpen_time() {
				return open_time;
			}
			public void setOpen_time(Date open_time) {
				this.open_time = open_time;
			}
			public Date getTime_out_time() {
				return time_out_time;
			}
			public void setTime_out_time(Date time_out_time) {
				this.time_out_time = time_out_time;
			}
			public String getOpen_user() {
				return open_user;
			}
			public void setOpen_user(String open_user) {
				this.open_user = open_user;
			}
			public String getRep_type() {
				return rep_type;
			}
			public void setRep_type(String rep_type) {
				this.rep_type = rep_type;
			}
			public String getStatues() {
				return statues;
			}
			public void setStatues(String statues) {
				this.statues = statues;
			}
			public String getRep_ref_file() {
				return rep_ref_file;
			}
			public void setRep_ref_file(String rep_ref_file) {
				this.rep_ref_file = rep_ref_file;
			}

			public String getOpen_user_ip() {
				return open_user_ip;
			}
			public void setOpen_user_ip(String open_user_ip) {
				this.open_user_ip = open_user_ip;
			}
			public String getRep_name() {
				return rep_name;
			}
			public void setRep_name(String rep_name) {
				this.rep_name = rep_name;
			}



}

//end ReportLock.java