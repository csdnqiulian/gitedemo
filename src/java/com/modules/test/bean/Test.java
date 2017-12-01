package com.modules.test.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.common.persistence.DataEntity;

public class Test extends DataEntity<Test>{
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date start_time;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	private Date endd_time;
	
	private String user_ids;
	private String user_names;
	
	private String s_name;
	private String s_time;
	private String e_time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEndd_time() {
		return endd_time;
	}
	public void setEndd_time(Date endd_time) {
		this.endd_time = endd_time;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_time() {
		return s_time;
	}
	public void setS_time(String s_time) {
		this.s_time = s_time;
	}
	public String getE_time() {
		return e_time;
	}
	public void setE_time(String e_time) {
		this.e_time = e_time;
	}
	public String getUser_ids() {
		return user_ids;
	}
	public void setUser_ids(String user_ids) {
		this.user_ids = user_ids;
	}
	public String getUser_names() {
		return user_names;
	}
	public void setUser_names(String user_names) {
		this.user_names = user_names;
	}
}
