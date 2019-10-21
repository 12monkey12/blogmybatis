package com.yc.bean;

import java.io.Serializable;

//实体类	主要是用来和数据库里面的表对象进行关联
public class User implements Serializable{
	private static final long serialVersionUID = -8589449217395820877L;
	
	private int uid;
	private String uname;
	private String pwd;
	private int isadmin;
	
	public User(int uid, String uname, String pwd, int isadmin) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.pwd = pwd;
		this.isadmin = isadmin;
	}
	
	public User() {
		super();
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", pwd=" + pwd + ", isadmin=" + isadmin + "]";
	}
	
}
